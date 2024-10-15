package com.foodcourt.FoodCourtMicroservice.domain.spi;

import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;

import java.util.Optional;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Optional<Restaurant> findRestaurantByEin(Integer ein);
    Optional<Restaurant> findRestaurantByName(String name);
    Optional<Restaurant> findById(Long id);
    Optional<Restaurant> findByUserId(Long userId);
    PagedResult<Restaurant> getAllRestaurants(Integer page, Integer size);
    Optional<Restaurant> findRestaurantByEmployeeId(Long userId);
    void saveEmployee(Long restaurantId, Long employeeId);
    Boolean existsById(Long id);
    Boolean isEmployeeAssigned(Long restaurantId, Long employeeId);
}
