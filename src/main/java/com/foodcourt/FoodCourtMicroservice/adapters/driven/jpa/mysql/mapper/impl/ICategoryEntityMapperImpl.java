package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.foodcourt.FoodCourtMicroservice.domain.model.Category;
import org.springframework.stereotype.Component;

@Component
public class ICategoryEntityMapperImpl implements ICategoryEntityMapper {
    @Override
    public CategoryEntity toEntity(Category category){
        if(category == null){
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        categoryEntity.setDescription(category.getDescription());

        return categoryEntity;
    }

    @Override
    public Category toModel(CategoryEntity categoryEntity){
        if(categoryEntity == null){
            return null;
        }
        Long id = categoryEntity.getId();
        String name = categoryEntity.getName();
        String description = categoryEntity.getDescription();

        return new Category(id, name, description);
    }
}
