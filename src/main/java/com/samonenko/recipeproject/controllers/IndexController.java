package com.samonenko.recipeproject.controllers;

import com.samonenko.recipeproject.domain.Category;
import com.samonenko.recipeproject.domain.UnitOfMeasure;
import com.samonenko.recipeproject.repositories.CategoryRepository;
import com.samonenko.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository uomRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository uomRepository) {
        this.categoryRepository = categoryRepository;
        this.uomRepository = uomRepository;
    }

    @RequestMapping("/")
    public String index() {
        Optional<Category> category = categoryRepository.findByName("Mexican");
        Optional<UnitOfMeasure> unitOfMeasure = uomRepository.findByType("Cup");
        System.out.println("Category id is: " + category.get().getId());
        System.out.println("Unit of measure id is: " + unitOfMeasure.get().getId());
        return "index";
    }

}
