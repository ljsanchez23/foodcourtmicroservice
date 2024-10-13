package com.foodcourt.FoodCourtMicroservice.domain.spi;

import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;

import java.util.Optional;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    Optional<Dish> findDishById(Long id);
    boolean existsByName(String name);
}
