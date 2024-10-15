package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findDishById(Long id);
    Optional<DishEntity> findByName(String name);
    Page<DishEntity> findByRestaurantId(Long restaurantId, Pageable pageable);

    Page<DishEntity> findByRestaurantIdAndCategoryId(Long restaurantId, Long categoryId, Pageable pageable);
}
