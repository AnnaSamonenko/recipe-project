package com.samonenko.recipeproject.dto;

import com.samonenko.recipeproject.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {

    private Long id;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String direction;
    private String description;
    private Byte[] image;
    private Difficulty difficulty;
    private NoteDTO note;
    private Set<IngredientDTO> ingredients = new HashSet<>();
    private Set<CategoryDTO> categories;

}
