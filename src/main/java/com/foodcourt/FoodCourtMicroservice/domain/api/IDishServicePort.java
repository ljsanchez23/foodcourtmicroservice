package com.foodcourt.FoodCourtMicroservice.domain.api;

import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;

public interface IDishServicePort {
    void saveDish(Dish dish);
}
