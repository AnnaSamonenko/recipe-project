package com.samonenko.recipeproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
