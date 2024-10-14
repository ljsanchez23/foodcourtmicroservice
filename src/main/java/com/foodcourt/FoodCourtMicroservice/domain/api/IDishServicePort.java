package com.foodcourt.FoodCourtMicroservice.domain.api;

import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDishStatus;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;

public interface IDishServicePort {
    void saveDish(Long dishId, Dish dish);
    void updateDish(Long userId, Long dishId, UpdateDish updateDish);
    void updateDishStatus(Long userId, Long dishIdh, UpdateDishStatus updateDishStatus);
}
