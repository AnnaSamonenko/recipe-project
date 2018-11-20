package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Ingredient;
import com.samonenko.recipeproject.dto.IngredientDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientDtoToIngredient implements Converter<IngredientDTO, Ingredient> {

    private final RecipeDtoToRecipe recipeConverter;
    private final UnitOfMeasureDtoToUnitOfMeasure uomConverter;

    public IngredientDtoToIngredient(RecipeDtoToRecipe recipeConverter, UnitOfMeasureDtoToUnitOfMeasure uomConverter) {
        this.recipeConverter = recipeConverter;
        this.uomConverter = uomConverter;
    }

    @Override
    public Ingredient convert(IngredientDTO ingredientDTO) {
        if (ingredientDTO == null)
            return null;
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getId());
        ingredient.setAmount(ingredientDTO.getAmount());
        ingredient.setDescription(ingredientDTO.getDescription());
        ingredient.setRecipe(recipeConverter.convert(ingredientDTO.getRecipe()));
        ingredient.setUnitOfMeasure(uomConverter.convert(ingredientDTO.getUnitOfMeasure()));
        return ingredient;
    }
}
