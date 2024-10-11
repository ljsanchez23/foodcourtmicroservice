package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.RestaurantRequest;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;

public interface IRestaurantRequestMapper {
    Restaurant toModel(RestaurantRequest restaurantRequest);
}
