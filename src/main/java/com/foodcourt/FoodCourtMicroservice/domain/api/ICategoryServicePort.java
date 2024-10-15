package com.foodcourt.FoodCourtMicroservice.domain.api;

import com.foodcourt.FoodCourtMicroservice.domain.model.Category;

public interface ICategoryServicePort {
    void saveCategory(Category category);
}
