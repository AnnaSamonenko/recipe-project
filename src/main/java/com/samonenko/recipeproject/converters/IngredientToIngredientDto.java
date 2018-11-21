package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Ingredient;
import com.samonenko.recipeproject.dto.IngredientDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientDto implements Converter<Ingredient, IngredientDTO> {

    private final RecipeToRecipeDto recipeConverter;
    private final UnitOfMeasureToUnitOfMeasureDTO uomConverter;

    public IngredientToIngredientDto(RecipeToRecipeDto recipeConverter, UnitOfMeasureToUnitOfMeasureDTO uomConverter) {
        this.recipeConverter = recipeConverter;
        this.uomConverter = uomConverter;
    }

    @Override
    public IngredientDTO convert(Ingredient ingredient) {
        if (ingredient == null)
            return null;
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());
        ingredientDTO.setDescription(ingredient.getDescription());
        ingredientDTO.setAmount(ingredient.getAmount());
        ingredientDTO.setRecipe(recipeConverter.convert(ingredient.getRecipe()));
        ingredientDTO.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
        return ingredientDTO;
    }
}