package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.services.ImageService;
import com.samonenko.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final ImageService imageService;

    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String uploadImagePage(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findRecipeById(id));
        return "recipe/upload_image";
    }

    @PostMapping("recipe/{id}/image")
    public String saveImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile multipartFile) {
        imageService.saveRecipeImage(Long.valueOf(id), multipartFile);
        return "redirect:/recipe/" + id + "/show";
    }
}
