package com.foodcourt.FoodCourtMicroservice.domain.api.impl;

import com.foodcourt.FoodCourtMicroservice.domain.api.IOrderServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.exception.OrderAlreadyInProgressException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.UnauthorizedUserException;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IOrderPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;

import java.util.Arrays;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
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

    @Override
    public PagedResult<Order> listOrders(Long userId, Integer page, Integer size, String status) {
        int actualPage = (page != null) ? page : Constants.DEFAULT_PAGE;
        int actualSize = (size != null) ? size : Constants.DEFAULT_SIZE;

        Restaurant restaurant = restaurantPersistencePort.findRestaurantByEmployeeId(userId)
                .orElseThrow(() -> new UnauthorizedUserException(Constants.UNAUTHORIZED_USER));

        return orderPersistencePort.findOrdersByRestaurantIdAndStatus(restaurant.getId(), status, actualPage, actualSize);
    }
}
