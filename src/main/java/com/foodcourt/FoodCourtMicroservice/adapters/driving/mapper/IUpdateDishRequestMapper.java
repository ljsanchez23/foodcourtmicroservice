package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper;


import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.UpdateDishRequest;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;

public interface IUpdateDishRequestMapper {
    UpdateDish toModel(UpdateDishRequest updateDishRequest);
}
