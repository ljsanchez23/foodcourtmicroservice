package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findCategoryById(Long id);
}
