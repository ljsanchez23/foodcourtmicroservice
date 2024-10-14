package com.foodcourt.FoodCourtMicroservice.domain.spi;

import com.foodcourt.FoodCourtMicroservice.domain.model.Category;

import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    Optional<Category> findCategoryById(Long id);
    boolean existsById(Long id);
}
