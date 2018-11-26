package com.samonenko.recipeproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientDTO {

    private Long id;

    private String description;

    private BigDecimal amount;

    private RecipeDTO recipe;

    private UnitOfMeasureDTO uom;

}
