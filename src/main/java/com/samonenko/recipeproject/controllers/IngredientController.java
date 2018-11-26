package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.services.IngredientService;
import com.samonenko.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping("/recipe/{id}/ingredients")
    public String listOfIngredients(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "/recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipe_id}/ingredient/{ingr_id}/show")
    public String showIngredient(@PathVariable("recipe_id") Long recipeId, @PathVariable("ingr_id") Long ingrId, Model model) {
        model.addAttribute("ingredient", ingredientService.findIngredientByIds(recipeId, ingrId));
        return "recipe/ingredient/show";
    }
}
