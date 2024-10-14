package com.foodcourt.FoodCourtMicroservice.domain.api.usecase;

import com.foodcourt.FoodCourtMicroservice.domain.api.impl.DishUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.exception.*;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.spi.ICategoryPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IDishPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDishStatus;
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

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

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
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(2L)));

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
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(2L)));

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
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(differentOwnerId)));
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
                .thenReturn(Optional.empty());

        UnauthorizedUserException thrown = assertThrows(
                UnauthorizedUserException.class,
                () -> dishUseCase.updateDish(userId, dishId, updateDish)
        );

        assertEquals(Constants.UNAUTHORIZED_USER, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(any(Dish.class));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_UPDATE_STATUS_SUCCESSFULLY)
    void shouldUpdateDishStatusSuccessfully() {
        Long userId = TestConstants.USER_ID;
        Long dishId = TestConstants.DISH_ID;
        UpdateDishStatus updateDishStatus = new UpdateDishStatus(true);

        Dish dish = TestDataFactory.createDefaultDish();
        dish.setStatus(false);

        when(restaurantPersistencePort.findByUserId(userId))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(userId)));
        when(dishPersistencePort.findDishById(dishId))
                .thenReturn(Optional.of(dish));

        dishUseCase.updateDishStatus(userId, dishId, updateDishStatus);

        assertTrue(dish.getStatus());
        verify(dishPersistencePort).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_DISH_IS_FROM_OTHER_RESTAURANT)
    void shouldThrowExceptionWhenDishBelongsToDifferentRestaurant() {
        Long userId = TestConstants.USER_ID;
        Long dishId = TestConstants.DISH_ID;
        UpdateDishStatus updateDishStatus = new UpdateDishStatus(true);

        Dish dish = TestDataFactory.createDefaultDish();
        dish.setRestaurantId(TestConstants.DIFFERENT_RESTAURANT_ID);

        when(restaurantPersistencePort.findByUserId(userId))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(userId)));
        when(dishPersistencePort.findDishById(dishId))
                .thenReturn(Optional.of(dish));

        UnauthorizedUserException thrown = assertThrows(
                UnauthorizedUserException.class,
                () -> dishUseCase.updateDishStatus(userId, dishId, updateDishStatus)
        );

        assertEquals(Constants.UNAUTHORIZED_USER, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_STATUS_IS_THE_SAME)
    void shouldThrowExceptionWhenNewStatusIsSameAsCurrentStatus() {
        Long userId = TestConstants.USER_ID;
        Long dishId = TestConstants.DISH_ID;
        UpdateDishStatus updateDishStatus = new UpdateDishStatus(true);

        Dish dish = TestDataFactory.createDefaultDish();
        dish.setStatus(true);

        when(restaurantPersistencePort.findByUserId(userId))
                .thenReturn(Optional.of(TestDataFactory.createRestaurantOwnedBy(userId)));
        when(dishPersistencePort.findDishById(dishId))
                .thenReturn(Optional.of(dish));

        StatusMustBeDifferentException thrown = assertThrows(
                StatusMustBeDifferentException.class,
                () -> dishUseCase.updateDishStatus(userId, dishId, updateDishStatus)
        );

        assertEquals(Constants.STATUS_MUST_BE_DIFFERENT_ERROR_MESSAGE, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_LIST_DISHES_BY_RESTAURANT)
    void shouldListDishesByRestaurantWithoutCategoryFilter() {
        Long restaurantId = TestConstants.RESTAURANT_ID;
        int page = Constants.DEFAULT_PAGE;
        int size = Constants.DEFAULT_SIZE;

        PagedResult<Dish> expectedDishes = TestDataFactory.createPagedResultDishes();

        when(dishPersistencePort.findDishesByRestaurant(restaurantId, page, size))
                .thenReturn(expectedDishes);

        PagedResult<Dish> actualDishes = dishUseCase.listDishes(restaurantId, page, size, null);

        assertEquals(expectedDishes, actualDishes);
        verify(dishPersistencePort).findDishesByRestaurant(restaurantId, page, size);
        verify(categoryPersistencePort, never()).existsById(anyLong());
    }

    @Test
    @DisplayName(TestConstants.SHOULD_LIST_DISHES_FILTERED)
    void shouldListDishesByRestaurantWithCategoryFilter() {
        Long restaurantId = TestConstants.RESTAURANT_ID;
        Long categoryId = TestConstants.CATEGORY_ID;
        int page = Constants.DEFAULT_PAGE;
        int size = Constants.DEFAULT_SIZE;

        PagedResult<Dish> expectedDishes = TestDataFactory.createPagedResultDishes();

        when(categoryPersistencePort.existsById(categoryId)).thenReturn(true);
        when(dishPersistencePort.findDishesByRestaurantAndCategory(restaurantId, categoryId, page, size))
                .thenReturn(expectedDishes);

        PagedResult<Dish> actualDishes = dishUseCase.listDishes(restaurantId, page, size, categoryId);

        assertEquals(expectedDishes, actualDishes);
        verify(categoryPersistencePort).existsById(categoryId);
        verify(dishPersistencePort).findDishesByRestaurantAndCategory(restaurantId, categoryId, page, size);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_CATEGORY_DOES_NOT_EXISTS)
    void shouldThrowCategoryDoesNotExistsExceptionWhenCategoryDoesNotExist() {
        Long restaurantId = TestConstants.RESTAURANT_ID;
        Long categoryId = TestConstants.CATEGORY_ID;
        int page = Constants.DEFAULT_PAGE;
        int size = Constants.DEFAULT_SIZE;

        when(categoryPersistencePort.existsById(categoryId)).thenReturn(false);

        CategoryDoesNotExistsException thrown = assertThrows(
                CategoryDoesNotExistsException.class,
                () -> dishUseCase.listDishes(restaurantId, page, size, categoryId)
        );

        assertEquals(Constants.CATEGORY_DOES_NOT_EXIST, thrown.getMessage());
        verify(categoryPersistencePort).existsById(categoryId);
        verify(dishPersistencePort, never()).findDishesByRestaurantAndCategory(anyLong(), anyLong(), anyInt(), anyInt());
        verify(dishPersistencePort, never()).findDishesByRestaurant(anyLong(), anyInt(), anyInt());
    }
}
