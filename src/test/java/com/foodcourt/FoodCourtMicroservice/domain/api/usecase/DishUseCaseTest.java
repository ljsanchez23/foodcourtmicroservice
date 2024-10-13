package com.foodcourt.FoodCourtMicroservice.domain.api.usecase;

import com.foodcourt.FoodCourtMicroservice.domain.api.impl.DishUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.exception.DishAlreadyExistsException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.DishDoesNotExistsException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.UnauthorizedUserException;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IDishPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;
import com.foodcourt.FoodCourtMicroservice.util.TestConstants;
import com.foodcourt.FoodCourtMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;

    @Test
    @DisplayName(TestConstants.SHOULD_SET_STATUS)
    void shouldSetStatusToTrueWhenStatusIsNull() {
        Dish dish = TestDataFactory.createDefaultDish();
        dish.setName(TestConstants.DEFAULT_DISH_NAME);
        dish.setStatus(null);

        when(dishPersistencePort.existsByName(dish.getName())).thenReturn(false);
        when(restaurantPersistencePort.findById(dish.getRestaurantId()))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(1L)));

        dishUseCase.saveDish(1L, dish);

        assertTrue(dish.getStatus(), TestConstants.DISH_STATUS_MESSAGE);
        verify(dishPersistencePort).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_DISH_EXISTS)
    void shouldThrowExceptionWhenDishAlreadyExists() {
        Dish dish = TestDataFactory.createDefaultDish();
        dish.setName(TestConstants.DEFAULT_DISH_NAME);
        dish.setStatus(true);

        when(dishPersistencePort.existsByName(dish.getName())).thenReturn(true);

        DishAlreadyExistsException thrown = assertThrows(
                DishAlreadyExistsException.class,
                () -> dishUseCase.saveDish(1L, dish)
        );

        assertEquals(Constants.DISH_NAME_MUST_BE_DIFFERENT, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_SAVE_DISH)
    void shouldSaveDishWhenValidationsPass() {
        Dish dish = TestDataFactory.createDefaultDish();
        dish.setName(TestConstants.DEFAULT_DISH_NAME);
        dish.setStatus(true);

        when(dishPersistencePort.existsByName(dish.getName())).thenReturn(false);
        when(restaurantPersistencePort.findById(dish.getRestaurantId()))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(1L)));

        dishUseCase.saveDish(1L, dish);

        verify(dishPersistencePort).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_NOT_RESTAURANT_OWNER)
    void shouldThrowExceptionWhenNotRestaurantOwner() {
        Dish dish = TestDataFactory.createDefaultDish();

        when(dishPersistencePort.existsByName(dish.getName())).thenReturn(false);
        when(restaurantPersistencePort.findById(dish.getRestaurantId()))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(2L))); // Otro propietario

        UnauthorizedUserException thrown = assertThrows(
                UnauthorizedUserException.class,
                () -> dishUseCase.saveDish(1L, dish)
        );

        assertEquals(Constants.UNAUTHORIZED_USER, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_UPDATE_DISH_PRICE_AND_DESCRIPTION)
    void shouldUpdateDishPriceAndDescription() {
        Long dishId = TestConstants.DISH_ID;
        Dish dish = TestDataFactory.createDefaultDish();
        UpdateDish updateDish = new UpdateDish();
        updateDish.setPrice(TestConstants.DISH_RANDOM_PRICE);
        updateDish.setDescription(TestConstants.DISH_DESCRIPTION);

        when(dishPersistencePort.findDishById(dishId)).thenReturn(Optional.of(dish));
        when(restaurantPersistencePort.findByUserId(TestConstants.USER_ID))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(TestConstants.USER_ID)));

        dishUseCase.updateDish(TestConstants.USER_ID, dishId, updateDish);

        assertEquals(TestConstants.DISH_RANDOM_PRICE, dish.getPrice());
        assertEquals(TestConstants.DISH_DESCRIPTION, dish.getDescription());
        verify(dishPersistencePort).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_UPDATING_DISH_NOT_OWNER)
    void shouldThrowExceptionWhenUpdatingDishNotOwner() {
        Long dishId = TestConstants.DISH_ID;
        Dish dish = TestDataFactory.createDefaultDish();
        UpdateDish updateDish = new UpdateDish();
        updateDish.setPrice(TestConstants.DISH_RANDOM_PRICE);
        updateDish.setDescription(TestConstants.DISH_DESCRIPTION);

        when(dishPersistencePort.findDishById(dishId)).thenReturn(Optional.of(dish));
        when(restaurantPersistencePort.findByUserId(TestConstants.USER_ID))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(2L))); // Otro propietario

        UnauthorizedUserException thrown = assertThrows(
                UnauthorizedUserException.class,
                () -> dishUseCase.updateDish(TestConstants.USER_ID, dishId, updateDish)
        );

        assertEquals(Constants.UNAUTHORIZED_USER, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_DISH_NOT_FOUND)
    void shouldThrowExceptionWhenDishNotFound() {
        Long dishId = TestConstants.DISH_ID;
        Long userId = TestConstants.USER_ID;

        UpdateDish updateDish = new UpdateDish();

        when(restaurantPersistencePort.findByUserId(userId))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(userId)));
        when(dishPersistencePort.findDishById(dishId))
                .thenReturn(Optional.empty());

        DishDoesNotExistsException thrown = assertThrows(
                DishDoesNotExistsException.class,
                () -> dishUseCase.updateDish(userId, dishId, updateDish)
        );

        assertEquals(Constants.DISH_DOES_NOT_EXISTS, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(any(Dish.class));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_USER_NOT_OWNER)
    void shouldThrowExceptionWhenUserNotOwner() {
        Long dishId = TestConstants.DISH_ID;
        Long userId = TestConstants.USER_ID;
        Long differentOwnerId = TestConstants.DIFFERENT_USER_ID;

        Dish dish = TestDataFactory.createDefaultDish();
        dish.setRestaurantId(TestConstants.RESTAURANT_ID);

        UpdateDish updateDish = new UpdateDish();

        when(restaurantPersistencePort.findByUserId(userId))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(differentOwnerId))); // Usuario diferente
        when(dishPersistencePort.findDishById(dishId))
                .thenReturn(Optional.of(dish));

        UnauthorizedUserException thrown = assertThrows(
                UnauthorizedUserException.class,
                () -> dishUseCase.updateDish(userId, dishId, updateDish)
        );

        assertEquals(Constants.UNAUTHORIZED_USER, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(any(Dish.class));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_UNAUTHORIZED_USER)
    void shouldThrowExceptionWhenUnauthorizedUser() {
        Long userId = TestConstants.USER_ID;
        Long dishId = TestConstants.DISH_ID;

        UpdateDish updateDish = new UpdateDish();

        when(restaurantPersistencePort.findByUserId(userId))
                .thenReturn(Optional.empty()); // Usuario no autorizado

        UnauthorizedUserException thrown = assertThrows(
                UnauthorizedUserException.class,
                () -> dishUseCase.updateDish(userId, dishId, updateDish)
        );

        assertEquals(Constants.UNAUTHORIZED_USER, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(any(Dish.class));
    }
}
