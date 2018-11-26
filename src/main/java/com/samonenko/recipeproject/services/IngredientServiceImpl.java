package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.converters.IngredientToIngredientDto;
import com.samonenko.recipeproject.domain.Ingredient;
import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.IngredientDTO;
import com.samonenko.recipeproject.repositories.RecipeRepository;
import com.samonenko.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository uomRepository;
    private final IngredientToIngredientDto converter;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository, IngredientToIngredientDto converter) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
        this.converter = converter;
    }

    @Override
    public IngredientDTO findIngredientByIds(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            log.error("Recipe with such id isn't present");
        }
        Recipe recipe = recipeOptional.get();
        Optional<IngredientDTO> ingredientDTO = recipe.getIngredients().
                stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> converter.convert(ingredient)).findFirst();
        if (!ingredientDTO.isPresent()) {
            log.error("Ingredient not found");
        }
        return ingredientDTO.get();
    }

    @Override
    public IngredientDTO save(IngredientDTO ingredientDTO) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientDTO.getRecipe().getId());
        if (!recipeOptional.isPresent()) {
            log.error("There no recipe with such id");
            return new IngredientDTO();
        }
        Recipe recipe = recipeOptional.get();
        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()) {

            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setAmount(ingredientDTO.getAmount());
            ingredientFound.setUnitOfMeasure(uomRepository.findById(ingredientDTO.getUom().getId())
                    .orElseThrow(() -> (new RuntimeException("UOM not found"))));
            ingredientFound.setDescription(ingredientDTO.getDescription());

        } else recipe.getIngredients().add(ingredientOptional.get());

        Recipe savedRecipe = recipeRepository.save(recipe);
        // if was added ingredient
        return converter
                .convert(savedRecipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                .findFirst().get());
    }
}
