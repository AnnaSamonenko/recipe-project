package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.converters.IngredientToIngredientDto;
import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.IngredientDTO;
import com.samonenko.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientDto converter;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientDto converter) {
        this.recipeRepository = recipeRepository;
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
}
