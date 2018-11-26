package com.samonenko.recipeproject.controller;

import com.samonenko.recipeproject.controllers.RecipeController;
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

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    private RecipeController recipeController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);

        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    // /recipe/{id}/show -- GET
    @Test
    public void testShowById() throws Exception {
        RecipeDTO recipe = new RecipeDTO();
        recipe.setId(1L);

        Mockito.when(recipeService.findRecipeById(1L)).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + recipe.getId() + "/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    // recipe/new -- GET
    @Test
    public void testGetAddRecipe() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"))
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipe_form"));
    }

    // recipe/{id}/update -- GET
    @Test
    public void testGetUpdateRecipe() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(1L);

        Mockito.when(recipeService.findRecipeById(recipeDTO.getId())).thenReturn(recipeDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + recipeDTO.getId() + "/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"))
                .andExpect(MockMvcResultMatchers.view().name("recipe/recipe_form"));
    }

    // /recipe - POST
    // TODO fix
    @Test
    public void testUpdateAndSaveRecipe() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(1L);

        Mockito.when(recipeService.findRecipeById(Mockito.anyLong())).thenReturn(recipeDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/recipe"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/" + recipeDTO.getId() + "/show"));
    }

}