package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new RecipeDTO());
        return "recipe/new_recipe";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeDTO recipeDTO) {
        RecipeDTO savedRecipeDto = recipeService.saveRecipe(recipeDTO);
        return "redirect:/recipe/" + savedRecipeDto.getId() + "/show";
    }
}
