package com.foodcourt.FoodCourtMicroservice.domain.spi;

import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;

import java.util.Optional;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Optional<Restaurant> findRestaurantByEin(Integer ein);
    Optional<Restaurant> findRestaurantByName(String name);
    Optional<Restaurant> findById(Long id);
    Optional<Restaurant> findByUserId(Long userId);
}
