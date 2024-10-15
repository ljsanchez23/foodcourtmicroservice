package com.foodcourt.FoodCourtMicroservice.domain.api.usecase;

import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.FoodCourtMicroservice.domain.api.impl.OrderUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.exception.NoEmployeeAuthentifiedException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.OrderAlreadyInProgressException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.OrderDoesNotExistsException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.UserNotAuthorizedException;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IOrderPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
import com.foodcourt.FoodCourtMicroservice.util.TestConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;


    @Test
    @DisplayName(TestConstants.SHOULD_THROW_EXCEPTION_WHEN_ORDER_ALREADY_IN_PROGRESS)
    void shouldThrowExceptionWhenOrderAlreadyInProgress() {

        Long userId = TestConstants.USER_ID;
        Order order = new Order();
        when(orderPersistencePort.existsByCustomerIdAndStatusIn(eq(userId), anyList()))
                .thenReturn(true);

        OrderAlreadyInProgressException exception = assertThrows(OrderAlreadyInProgressException.class, () -> {
            orderUseCase.createOrder(userId, order);
        });

        assertEquals(Constants.ORDER_ALREADY_IN_PROCESS, exception.getMessage());
        verify(orderPersistencePort, never()).saveOrder(any(Order.class));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_SAVE_ORDER_WHEN_NO_ORDER_IS_IN_PROGRESS)
    void shouldSaveOrderWhenNoOrderInProgress() {

        Long userId = TestConstants.USER_ID;
        Order order = new Order();
        when(orderPersistencePort.existsByCustomerIdAndStatusIn(eq(userId), anyList()))
                .thenReturn(false);

        orderUseCase.createOrder(userId, order);

        assertEquals(userId, order.getCustomerId());
        assertEquals(Constants.PENDING_STATUS, order.getStatus());
        verify(orderPersistencePort).saveOrder(order);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_RETURN_ORDERS_PAGE_RESULT)
    void shouldReturnPagedResultWhenValidUserIdAndRestaurantFound() {
        Long userId = TestConstants.USER_ID;
        Long restaurantId = TestConstants.RESTAURANT_ID;
        int page = Constants.DEFAULT_PAGE;
        int size = Constants.DEFAULT_SIZE;
        String status = TestConstants.COMPLETED_STATUS;

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);

        PagedResult<Order> expectedPagedResult = new PagedResult<>(new ArrayList<>(), page, size, TestConstants.TOTAL_ELEMENTS);

        when(restaurantPersistencePort.findRestaurantByEmployeeId(userId)).thenReturn(Optional.of(restaurant));

        when(orderPersistencePort.findOrdersByRestaurantIdAndStatus(restaurantId, status, page, size)).thenReturn(expectedPagedResult);

        PagedResult<Order> actualPagedResult = orderUseCase.listOrders(userId, page, size, status);

        assertEquals(expectedPagedResult, actualPagedResult);
        verify(restaurantPersistencePort).findRestaurantByEmployeeId(userId);
        verify(orderPersistencePort).findOrdersByRestaurantIdAndStatus(restaurantId, status, page, size);
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROWN_EXCEPTION_WHEN_ORDER_DOES_NOT_EXISTS)
    void shouldThrowExceptionWhenOrderDoesNotExist() {
        Long orderId = TestConstants.ORDER_ID;
        Long userId = TestConstants.USER_ID;
        String status = TestConstants.ASSIGNED_STATUS;

        when(orderPersistencePort.existsById(orderId)).thenReturn(false);

        OrderDoesNotExistsException exception = assertThrows(OrderDoesNotExistsException.class, () -> {
            orderUseCase.assignOrder(orderId, userId, status);
        });

        assertEquals(Constants.ORDER_DOES_NOT_EXISTS_ERROR_MESSAGE, exception.getMessage());
        verify(orderPersistencePort, times(1)).existsById(orderId);
        verify(orderPersistencePort, never()).assignOrder(anyLong(), anyLong(), anyString());
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROWN_EXCEPTION_WHEN_USER_IS_NOT_AUTHENTICATED)
    void shouldThrowExceptionWhenUserIsNotAuthenticated() {
        Long orderId = TestConstants.ORDER_ID;
        String status = TestConstants.ASSIGNED_STATUS;

        when(orderPersistencePort.existsById(orderId)).thenReturn(true);

        NoEmployeeAuthentifiedException exception = assertThrows(NoEmployeeAuthentifiedException.class, () -> {
            orderUseCase.assignOrder(orderId, null, status);
        });

        assertEquals(Constants.EMPLOYEE_IS_NOT_AUTHENTICATED, exception.getMessage());
        verify(orderPersistencePort, times(1)).existsById(orderId);
        verify(orderPersistencePort, never()).assignOrder(anyLong(), anyLong(), anyString());
    }

    @Test
    @DisplayName(TestConstants.SHOULD_THROWN_EXCEPTION_WHEN_USER_IS_NOT_A_RESTAURANT_EMPLOYEE)
    void shouldThrowExceptionWhenUserNotEmployeeOfRestaurant() {
        Long orderId = TestConstants.ORDER_ID;
        Long userId = TestConstants.USER_ID;
        String status = TestConstants.ASSIGNED_STATUS;
        Order order = new Order();
        order.setRestaurantId(TestConstants.RESTAURANT_ID);

        when(orderPersistencePort.existsById(orderId)).thenReturn(true);
        when(orderPersistencePort.findById(orderId)).thenReturn(Optional.of(order));
        when(restaurantPersistencePort.isEmployeeAssigned(order.getRestaurantId(), userId)).thenReturn(false);

        UserNotAuthorizedException exception = assertThrows(UserNotAuthorizedException.class, () -> {
            orderUseCase.assignOrder(orderId, userId, status);
        });

        assertEquals(Constants.USER_IS_NOT_EMPLOYEE_OF_RESTAURANT, exception.getMessage());
        verify(orderPersistencePort, times(1)).existsById(orderId);
        verify(orderPersistencePort, times(1)).findById(orderId);
        verify(restaurantPersistencePort, times(1)).isEmployeeAssigned(order.getRestaurantId(), userId);
        verify(orderPersistencePort, never()).assignOrder(anyLong(), anyLong(), anyString());
    }

    @Test
    @DisplayName(TestConstants.SHOULD_ASSIGN_ORDER_WHEN_VALIDATION_PASS)
    void shouldAssignOrderWhenValidationsPass() {
        Long orderId = TestConstants.ORDER_ID;
        Long userId = TestConstants.USER_ID;
        String status = TestConstants.ASSIGNED_STATUS;
        Order order = new Order();
        order.setRestaurantId(TestConstants.RESTAURANT_ID);

        when(orderPersistencePort.existsById(orderId)).thenReturn(true);
        when(orderPersistencePort.findById(orderId)).thenReturn(Optional.of(order));
        when(restaurantPersistencePort.isEmployeeAssigned(order.getRestaurantId(), userId)).thenReturn(true);

        orderUseCase.assignOrder(orderId, userId, status);

        verify(orderPersistencePort, times(1)).existsById(orderId);
        verify(orderPersistencePort, times(1)).findById(orderId);
        verify(restaurantPersistencePort, times(1)).isEmployeeAssigned(order.getRestaurantId(), userId);
        verify(orderPersistencePort, times(1)).assignOrder(orderId, userId, status);
    }
}
