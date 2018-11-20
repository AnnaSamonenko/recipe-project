package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.RecipeDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeDto implements Converter<Recipe, RecipeDTO> {

    @Override
    public RecipeDTO convert(Recipe recipe) {
        return null;
    }
}
