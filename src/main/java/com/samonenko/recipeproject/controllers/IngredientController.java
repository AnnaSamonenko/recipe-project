package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.dto.IngredientDTO;
import com.samonenko.recipeproject.dto.RecipeDTO;
import com.samonenko.recipeproject.dto.UnitOfMeasureDTO;
import com.samonenko.recipeproject.services.IngredientService;
import com.samonenko.recipeproject.services.RecipeService;
import com.samonenko.recipeproject.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    private final UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
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

    @PostMapping
    @RequestMapping("/recipe/{recipe_id}/ingredient")
    public String saveOrUpdate(@ModelAttribute("ingredient") IngredientDTO ingredientDTO, @PathVariable("recipe_id") Long recipeId) {
        IngredientDTO savedIngredient = ingredientService.save(ingredientDTO);
        return "redirect:/recipe/" + savedIngredient.getRecipeId() + "/ingredient/"
                + savedIngredient.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model) {
        model.addAttribute("ingredient", ingredientService.findIngredientByIds(Long.valueOf(recipeId), Long.valueOf(id)));

        model.addAttribute("uomList", uomService.listOfUOMs());
        return "recipe/ingredient/ingredient_form";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String createNewRecipeIngredient(@PathVariable String recipeId, Model model) {
        RecipeDTO recipe = recipeService.findRecipeById(Long.valueOf(recipeId));
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setRecipeId(recipe.getId());
        ingredientDTO.setUom(new UnitOfMeasureDTO());
        model.addAttribute("ingredient", ingredientDTO);

        model.addAttribute("uomList", uomService.listOfUOMs());
        return "recipe/ingredient/ingredient_form";
    }

}
