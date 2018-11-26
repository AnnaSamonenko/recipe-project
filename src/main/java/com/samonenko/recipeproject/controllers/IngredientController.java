package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/ingredients")
    public String listOfIngredients(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "/recipe/ingredient/list";
    }

}
