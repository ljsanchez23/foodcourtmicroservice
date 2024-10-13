package com.foodcourt.FoodCourtMicroservice.domain.api.usecase;

import com.foodcourt.FoodCourtMicroservice.domain.api.impl.DishUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.exception.DishAlreadyExistsException;
import com.foodcourt.FoodCourtMicroservice.domain.model.Category;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IDishPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.util.TestConstants;
import com.foodcourt.FoodCourtMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.foodcourt.FoodCourtMicroservice.domain.util.Validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @InjectMocks
    private DishUseCase dishUseCase;


    @Test
    @DisplayName(TestConstants.SHOULD_SET_STATUS)
    void shouldSetStatusToTrueWhenStatusIsNull() {
        Dish dish = TestDataFactory.createDefaultDish();
        dish.setName(TestConstants.DEFAULT_DISH_NAME);
        dish.setStatus(null);

        when(dishPersistencePort.existsByName(dish.getName())).thenReturn(false);

        dishUseCase.saveDish(dish);

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
                () -> dishUseCase.saveDish(dish)
        );

        assertEquals(Constants.DISH_ALREADY_EXISTS, thrown.getMessage());
        verify(dishPersistencePort, never()).saveDish(dish);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_SAVE_DISH)
    void shouldSaveDishWhenValidationsPass() {
        Dish dish = TestDataFactory.createDefaultDish();
        dish.setName(TestConstants.DEFAULT_DISH_NAME);
        dish.setStatus(true);

        when(dishPersistencePort.existsByName(dish.getName())).thenReturn(false);

        dishUseCase.saveDish(dish);

        verify(dishPersistencePort).saveDish(dish);
    }

}
