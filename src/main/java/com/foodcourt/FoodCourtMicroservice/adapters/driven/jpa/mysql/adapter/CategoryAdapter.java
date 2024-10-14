package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.foodcourt.FoodCourtMicroservice.domain.model.Category;
import com.foodcourt.FoodCourtMicroservice.domain.spi.ICategoryPersistencePort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category){
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id)
                .map(categoryEntityMapper::toModel);
    }

    @Override
    public boolean existsById(Long id){
        return categoryRepository.existsById(id);
    }
}