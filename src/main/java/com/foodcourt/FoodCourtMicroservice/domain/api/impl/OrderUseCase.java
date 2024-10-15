package com.foodcourt.FoodCourtMicroservice.domain.api.impl;

import com.foodcourt.FoodCourtMicroservice.domain.api.IOrderServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.exception.OrderAlreadyInProgressException;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IOrderPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;

import java.util.Arrays;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void createOrder(Long userId, Order order) {
        if(Boolean.TRUE.equals(orderPersistencePort.existsByCustomerIdAndStatusIn(userId, Arrays.asList(Constants.PENDING_STATUS, Constants.PREPARING_STATUS, Constants.READY_STATUS)))){
            throw new OrderAlreadyInProgressException(Constants.ORDER_ALREADY_IN_PROCESS);
        }
        order.setCustomerId(userId);
        order.setStatus(Constants.PENDING_STATUS);
        orderPersistencePort.saveOrder(order);
    }
}
