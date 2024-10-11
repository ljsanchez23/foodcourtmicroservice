package com.foodcourt.FoodCourtMicroservice.domain.api.usecase;

import com.foodcourt.FoodCourtMicroservice.domain.api.impl.RestaurantUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.exception.RestaurantAlreadyExistsException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.InvalidRoleException;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantUseCaseTest {

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;
    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private RestaurantUseCase restaurantUseCase;

    @Test
    @DisplayName(TestConstants.RESTAURANT_ALREADY_EXISTS_EXCEPTION_EIN)
    void shouldThrowExceptionWhenRestaurantEinAlreadyExists() {
        Restaurant restaurant = TestDataFactory.createDefaultRestaurant();
        restaurant.setEin(TestConstants.VALID_EIN);

        when(restaurantPersistencePort.findRestaurantByEin(restaurant.getEin())).thenReturn(Optional.of(restaurant));

        RestaurantAlreadyExistsException thrown =
                assertThrows(RestaurantAlreadyExistsException.class, () -> restaurantUseCase.saveRestaurant(restaurant));

        assertEquals(Constants.RESTAURANT_ALREADY_EXISTS, thrown.getMessage());
    }

    @Test
    @DisplayName(TestConstants.RESTAURANT_ALREADY_EXISTS_EXCEPTION_NAME)
    void shouldThrowExceptionWhenRestaurantNameAlreadyExists() {
        Restaurant restaurant = TestDataFactory.createDefaultRestaurant();
        restaurant.setName(TestConstants.VALID_RESTAURANT_NAME);

        when(restaurantPersistencePort.findRestaurantByName(restaurant.getName())).thenReturn(Optional.of(restaurant));

        RestaurantAlreadyExistsException thrown =
                assertThrows(RestaurantAlreadyExistsException.class, () -> restaurantUseCase.saveRestaurant(restaurant));

        assertEquals(Constants.RESTAURANT_NAME_MUST_BE_DIFFERENT, thrown.getMessage());
    }

    @Test
    @DisplayName(TestConstants.INVALID_ROLE_EXCEPTION)
    void shouldThrowInvalidRoleExceptionWhenUserIsNotOwner() {
        Restaurant restaurant = TestDataFactory.createDefaultRestaurant();

        when(restaurantPersistencePort.findRestaurantByEin(restaurant.getEin())).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findRestaurantByName(restaurant.getName())).thenReturn(Optional.empty());
        when(userPersistencePort.getRole(restaurant.getUserId())).thenReturn(TestConstants.ROLE_CUSTOMER);

        InvalidRoleException thrown =
                assertThrows(InvalidRoleException.class, () -> restaurantUseCase.saveRestaurant(restaurant));

        assertEquals(Constants.INVALID_ROLE_MESSAGE, thrown.getMessage());
    }

    @Test
    @DisplayName(TestConstants.SHOULD_SAVE_RESTAURANT)
    void shouldSaveRestaurantWhenValidationsPass() {
        Restaurant restaurant = TestDataFactory.createDefaultRestaurant();

        when(restaurantPersistencePort.findRestaurantByEin(restaurant.getEin())).thenReturn(Optional.empty());
        when(restaurantPersistencePort.findRestaurantByName(restaurant.getName())).thenReturn(Optional.empty());
        when(userPersistencePort.getRole(restaurant.getUserId())).thenReturn(Constants.ROLE_OWNER);

        restaurantUseCase.saveRestaurant(restaurant);

        verify(restaurantPersistencePort).saveRestaurant(restaurant);
    }
}
