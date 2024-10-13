package com.foodcourt.FoodCourtMicroservice.domain.api;

import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;

public interface IDishServicePort {
    void saveDish(Long dishId, Dish dish);
    void updateDish(Long userId, Long dishId, UpdateDish updateDish);
}
