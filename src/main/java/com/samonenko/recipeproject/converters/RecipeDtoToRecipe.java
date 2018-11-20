package com.samonenko.recipeproject.converters;

import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.dto.RecipeDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeDtoToRecipe implements Converter<RecipeDTO, Recipe> {

    private NoteDtoToNote noteConverter;

    @Override
    public Recipe convert(RecipeDTO recipeDTO) {
        if (recipeDTO == null)
            return null;
        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.getId());
        recipe.setCookTime(recipeDTO.getCookTime());
        recipe.setPrepTime(recipeDTO.getPrepTime());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setDifficulty(recipeDTO.getDifficulty());
        recipe.setDirection(recipeDTO.getDirection());
        recipe.setServings(recipeDTO.getServings());
        recipe.setSource(recipeDTO.getSource());
        recipe.setUrl(recipeDTO.getUrl());
        recipe.setNote(noteConverter.convert(recipeDTO.getNote()));

        recipe.setIngredients();
        recipe.setCategories();

        return recipe;
    }
}
