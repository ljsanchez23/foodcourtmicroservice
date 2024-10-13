package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.foodcourt.FoodCourtMicroservice.domain.model.Category;

public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toModel(CategoryEntity categoryEntity);
}
