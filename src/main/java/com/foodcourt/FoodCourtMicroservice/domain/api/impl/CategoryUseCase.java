package com.foodcourt.FoodCourtMicroservice.domain.api.impl;

import com.foodcourt.FoodCourtMicroservice.domain.api.ICategoryServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.model.Category;
import com.foodcourt.FoodCourtMicroservice.domain.spi.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category){
        if(categoryPersistencePort.findCategoryById(category.getId()).isPresent()){
            return;
        }
        categoryPersistencePort.saveCategory(category);
    }
}
