package com.samonenko.recipeproject.repositories;

import com.samonenko.recipeproject.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
