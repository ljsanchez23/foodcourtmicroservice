package com.foodcourt.FoodCourtMicroservice.domain.api.impl;

import com.foodcourt.FoodCourtMicroservice.domain.api.IDishServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.exception.*;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IDishPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDishStatus;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;
import com.foodcourt.FoodCourtMicroservice.domain.util.Validator;

import java.util.Objects;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void saveDish(Long userId, Dish dish) {
        Validator.validateDish(dish);

        validateDishExists(dish);

        validateRestaurantOwnership(userId, dish.getRestaurantId());

        if (dish.getStatus() == null) {
            dish.setStatus(true);
        }
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public void updateDish(Long userId, Long dishId, UpdateDish updateDish) {
        Long restaurantId = restaurantPersistencePort.findByUserId(userId)
                .orElseThrow(() -> new UnauthorizedUserException(Constants.UNAUTHORIZED_USER)).getUserId();

        Dish dish = dishPersistencePort.findDishById(dishId)
                .orElseThrow(() -> new DishDoesNotExistsException(Constants.DISH_DOES_NOT_EXISTS));

        if (!Objects.equals(dish.getRestaurantId(), restaurantId)) {
            throw new UnauthorizedUserException(Constants.UNAUTHORIZED_USER);
        }

        if (updateDish.getPrice() != null) {
            dish.setPrice(updateDish.getPrice());
        }

        if (updateDish.getDescription() != null) {
            dish.setDescription(updateDish.getDescription());
        }

        dishPersistencePort.saveDish(dish);
    }

    @Override
    public void updateDishStatus(Long userId, Long dishId, UpdateDishStatus updateDishStatus){
        Long restaurantId = restaurantPersistencePort.findByUserId(userId)
                .orElseThrow(() -> new UnauthorizedUserException(Constants.UNAUTHORIZED_USER)).getUserId();

        Dish dish = dishPersistencePort.findDishById(dishId)
                .orElseThrow(() -> new DishDoesNotExistsException(Constants.DISH_DOES_NOT_EXISTS));

        if (!Objects.equals(dish.getRestaurantId(), restaurantId)) {
            throw new UnauthorizedUserException(Constants.UNAUTHORIZED_USER);
        }
        if(Boolean.TRUE.equals(dish.getStatus()) == updateDishStatus.isNewStatus()){
            throw new StatusMustBeDifferentException(Constants.STATUS_MUST_BE_DIFFERENT_ERROR_MESSAGE);
        }
        dish.setStatus(updateDishStatus.isNewStatus());
        dishPersistencePort.saveDish(dish);
    }

    protected void validateDishExists(Dish dish) {
        if (dishPersistencePort.findDishById(dish.getId()).isPresent()) {
            throw new DishAlreadyExistsException(Constants.DISH_ALREADY_EXISTS);
        }
        if (dishPersistencePort.existsByName(dish.getName())) {
            throw new DishAlreadyExistsException(Constants.DISH_NAME_MUST_BE_DIFFERENT);
        }
    }

    protected void validateRestaurantOwnership(Long userId, Long restaurantId) {
        Restaurant restaurant = restaurantPersistencePort.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(Constants.RESTAURANT_NOT_FOUND));

        if (!Objects.equals(userId, restaurant.getUserId())) {
            throw new UnauthorizedUserException(Constants.UNAUTHORIZED_USER);
        }
    }
}
