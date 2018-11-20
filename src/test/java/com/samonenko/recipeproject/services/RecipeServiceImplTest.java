package com.samonenko.recipeproject.services;

import com.samonenko.recipeproject.domain.Recipe;
import com.samonenko.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import static org.junit.Assert.*;

public class RecipeServiceImplTest {

    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipesTest() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);

        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

        Set<Recipe> recipesActual = recipeService.getRecipes();

        assertEquals(1, recipesActual.size());
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findRecipeByIdTest() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Mockito.when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        assertEquals(recipe, recipeService.findById(1L));
        Mockito.verify(recipeRepository,
                Mockito.times(1)).findById(1L);
    }
}