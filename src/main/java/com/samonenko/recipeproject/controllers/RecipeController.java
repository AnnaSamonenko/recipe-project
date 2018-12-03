package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.exceptions.NotFoundException;
import com.samonenko.recipeproject.services.RecipeService;
import org.springframework.http.HttpStatus;
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
        return "recipe/recipe_form";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "recipe/recipe_form";
    }

    @PostMapping
    @RequestMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeDTO recipeDTO) {
        RecipeDTO savedRecipeDto = recipeService.saveRecipe(recipeDTO);
        return "redirect:/recipe/" + savedRecipeDto.getId() + "/show";
    }

    @DeleteMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "404";
    }
}
