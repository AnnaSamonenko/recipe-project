package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class IngredientControllerTest {

    @Mock
    private RecipeService recipeService;

    private MockMvc mockMvc;

    private IngredientController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testListOfIngredients() throws Exception {
        RecipeDTO recipe = new RecipeDTO();

        Mockito.when(recipeService.findRecipeById(Mockito.anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/ingredients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipe/ingredient/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }
}