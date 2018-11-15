package com.samonenko.recipeproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private Integer prepTime;

    @Getter
    @Setter
    private Integer cookTime;

    @Getter
    @Setter
    private Integer servings;

    @Getter
    @Setter
    private String source;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    @Lob
    private String description;

    @Getter
    @Setter
    @Lob
    private Byte[] image;

    @Getter
    @Setter
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();
}
