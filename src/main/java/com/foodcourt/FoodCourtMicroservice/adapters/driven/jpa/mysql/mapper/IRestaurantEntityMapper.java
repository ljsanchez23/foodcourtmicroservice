package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;

public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(Restaurant restaurant);
    Restaurant toModel(RestaurantEntity restaurantEntity);
}
