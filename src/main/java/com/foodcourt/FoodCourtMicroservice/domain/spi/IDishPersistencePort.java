package com.foodcourt.FoodCourtMicroservice.domain.spi;

import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;

import java.util.Optional;

public interface IDishPersistencePort {
    void saveDish(Dish dish);
    Optional<Dish> findDishById(Long id);
    boolean existsByName(String name);
    PagedResult<Dish> findDishesByRestaurant(Long restaurantId, int page, int size);
    PagedResult<Dish> findDishesByRestaurantAndCategory(Long restaurantId, Long categoryId, int page, int size);
}
