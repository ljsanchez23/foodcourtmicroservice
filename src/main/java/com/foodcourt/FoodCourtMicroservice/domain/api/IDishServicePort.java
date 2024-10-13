package com.foodcourt.FoodCourtMicroservice.domain.api;

import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;

public interface IDishServicePort {
    void saveDish(Dish dish);
    void updateDish(Long dishId, UpdateDish updateDish);
}
