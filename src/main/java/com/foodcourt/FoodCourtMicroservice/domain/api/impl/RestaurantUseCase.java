package com.foodcourt.FoodCourtMicroservice.domain.api.impl;

import com.foodcourt.FoodCourtMicroservice.domain.api.IRestaurantServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.exception.InvalidRoleException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.RestaurantAlreadyExistsException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.UnauthorizedUserException;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IUserPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
import com.foodcourt.FoodCourtMicroservice.domain.util.Validator;

import java.util.Objects;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserPersistencePort userPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveRestaurant(Long userId, Restaurant restaurant) {
        Validator.validateRestaurant(restaurant);

        if (restaurantPersistencePort.findRestaurantByEin(restaurant.getEin()).isPresent()) {
            throw new RestaurantAlreadyExistsException(Constants.RESTAURANT_ALREADY_EXISTS);
        }
        if (restaurantPersistencePort.findRestaurantByName(restaurant.getName()).isPresent()) {
            throw new RestaurantAlreadyExistsException(Constants.RESTAURANT_NAME_MUST_BE_DIFFERENT);
        }
        String userRole = userPersistencePort.getRole(userId);
        if (!Objects.equals(userRole, Constants.ROLE_OWNER)) {
            throw new InvalidRoleException(Constants.INVALID_ROLE_MESSAGE);
        }

        if (!Objects.equals(userId, restaurant.getUserId())) {
            throw new UnauthorizedUserException(Constants.UNAUTHORIZED_USER);
        }
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public PagedResult<Restaurant> listRestaurants(Integer page, Integer size){
        int defaultPage = Constants.DEFAULT_PAGE;
        int defaultSize = Constants.DEFAULT_SIZE;

        int actualPage = (page != null) ? page : defaultPage;
        int actualSize = (size != null) ? size : defaultSize;

        return restaurantPersistencePort.getAllRestaurants(actualPage, actualSize);

    }
}
