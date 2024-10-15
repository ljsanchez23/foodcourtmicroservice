package com.foodcourt.FoodCourtMicroservice.domain.api;

import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;

public interface IOrderServicePort {
    void createOrder(Long userId, Order order);
    PagedResult<Order> listOrders(Long userId, Integer page, Integer size, String status);
}
