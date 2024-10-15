package com.foodcourt.FoodCourtMicroservice.domain.api;

import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;

public interface IRestaurantServicePort {
    void saveRestaurant(Long userId, Restaurant restaurant);
    PagedResult<Restaurant> listRestaurants(Integer page, Integer size);
}
