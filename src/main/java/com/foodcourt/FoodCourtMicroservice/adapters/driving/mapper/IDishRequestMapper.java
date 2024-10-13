package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.DishRequest;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;

public interface IDishRequestMapper {
    Dish toModel(DishRequest dishRequest);
}
