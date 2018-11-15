package com.samonenko.recipeproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class Ingredient {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private BigDecimal amount;

    @Getter
    @Setter
    @ManyToOne
    private Recipe recipe;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.amount = amount;
        this.description = description;
        this.unitOfMeasure = uom;
        this.recipe = recipe;
    }
}
