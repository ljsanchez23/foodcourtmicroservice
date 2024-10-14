package com.foodcourt.FoodCourtMicroservice.domain.api.usecase;

import com.foodcourt.FoodCourtMicroservice.domain.api.impl.RestaurantUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.exception.RestaurantAlreadyExistsException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.InvalidRoleException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.UnauthorizedUserException;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IUserPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.util.TestConstants;
import com.foodcourt.FoodCourtMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @Test
    @DisplayName(TestConstants.SHOULD_SAVE_RESTAURANT_WHEN_VALIDATIONS_PASS)
    void shouldSaveRestaurantWhenValidationsPass() {
        Long userId = TestConstants.USER_ID;
        Restaurant restaurant = TestDataFactory.createDefaultRestaurant();

        when(restaurantPersistencePort.findRestaurantByEin(restaurant.getEin())).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findRestaurantByName(restaurant.getName())).thenReturn(Optional.empty());
        when(userPersistencePort.getRole(userId)).thenReturn(Constants.ROLE_OWNER);

        restaurantUseCase.saveRestaurant(userId, restaurant);

        verify(restaurantPersistencePort).saveRestaurant(restaurant);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_RESTAURANT_EXISTS_BY_EIN)
    void shouldThrowExceptionWhenRestaurantExistsByEin() {
        Long userId = TestConstants.USER_ID;
        Restaurant restaurant = TestDataFactory.createDefaultRestaurant();

        when(restaurantPersistencePort.findRestaurantByEin(restaurant.getEin()))
                .thenReturn(Optional.of(restaurant));

        RestaurantAlreadyExistsException thrown = assertThrows(
                RestaurantAlreadyExistsException.class,
                () -> restaurantUseCase.saveRestaurant(userId, restaurant)
        );

        assertEquals(Constants.RESTAURANT_ALREADY_EXISTS, thrown.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_RESTAURANT_EXISTS_BY_NAME)
    void shouldThrowExceptionWhenRestaurantExistsByName() {
        Long userId = TestConstants.USER_ID;
        Restaurant restaurant = TestDataFactory.createDefaultRestaurant();

        when(restaurantPersistencePort.findRestaurantByEin(restaurant.getEin())).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findRestaurantByName(restaurant.getName()))
                .thenReturn(Optional.of(restaurant));

        RestaurantAlreadyExistsException thrown = assertThrows(
                RestaurantAlreadyExistsException.class,
                () -> restaurantUseCase.saveRestaurant(userId, restaurant)
        );

        assertEquals(Constants.RESTAURANT_NAME_MUST_BE_DIFFERENT, thrown.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_USER_IS_NOT_OWNER)
    void shouldThrowExceptionWhenUserIsNotOwner() {
        Long userId = TestConstants.USER_ID;
        Restaurant restaurant = TestDataFactory.createDefaultRestaurant();

        when(restaurantPersistencePort.findRestaurantByEin(restaurant.getEin())).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findRestaurantByName(restaurant.getName())).thenReturn(Optional.empty());
        when(userPersistencePort.getRole(userId)).thenReturn(Constants.ROLE_ADMIN);

        InvalidRoleException thrown = assertThrows(
                InvalidRoleException.class,
                () -> restaurantUseCase.saveRestaurant(userId, restaurant)
        );

        assertEquals(Constants.INVALID_ROLE_MESSAGE, thrown.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_USER_NOT_AUTHORIZED)
    void shouldThrowExceptionWhenUserNotAuthorized() {
        Long userId = TestConstants.USER_ID;
        Restaurant restaurant = TestDataFactory.createDefaultRestaurant();
        restaurant.setUserId(TestConstants.DIFFERENT_USER_ID);

        when(restaurantPersistencePort.findRestaurantByEin(restaurant.getEin())).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findRestaurantByName(restaurant.getName())).thenReturn(Optional.empty());
        when(userPersistencePort.getRole(userId)).thenReturn(Constants.ROLE_OWNER);

        UnauthorizedUserException thrown = assertThrows(
                UnauthorizedUserException.class,
                () -> restaurantUseCase.saveRestaurant(userId, restaurant)
        );

        assertEquals(Constants.UNAUTHORIZED_USER, thrown.getMessage());
        verify(restaurantPersistencePort, never()).saveRestaurant(any(Restaurant.class));
    }
}
