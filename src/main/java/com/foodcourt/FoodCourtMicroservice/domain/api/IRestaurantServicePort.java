package com.foodcourt.FoodCourtMicroservice.domain.api;

import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;

public interface IRestaurantServicePort {
    void saveRestaurant(Long userId, Restaurant restaurant);
}
