package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findDishById(Long id);
    Optional<DishEntity> findByName(String name);
}
