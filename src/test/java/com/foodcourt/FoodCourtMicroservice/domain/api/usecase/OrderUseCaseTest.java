package com.foodcourt.FoodCourtMicroservice.domain.api.usecase;

import com.foodcourt.FoodCourtMicroservice.domain.api.impl.OrderUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.exception.OrderAlreadyInProgressException;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IOrderPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.util.TestConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    @Mock
    private IOrderPersistencePort orderPersistencePort;

    @InjectMocks
    private OrderUseCase orderUseCase;


    @Test
    void createOrder_ShouldThrowException_WhenOrderAlreadyInProgress() {

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
    void createOrder_ShouldSaveOrder_WhenNoOrderInProgress() {

        Long userId = TestConstants.USER_ID;
        Order order = new Order();
        when(orderPersistencePort.existsByCustomerIdAndStatusIn(eq(userId), anyList()))
                .thenReturn(false);

        orderUseCase.createOrder(userId, order);

        assertEquals(userId, order.getCustomerId());
        assertEquals(Constants.PENDING_STATUS, order.getStatus());
        verify(orderPersistencePort).saveOrder(order);
    }
}
