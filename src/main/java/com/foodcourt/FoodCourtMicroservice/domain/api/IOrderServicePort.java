package com.foodcourt.FoodCourtMicroservice.domain.api;

import com.foodcourt.FoodCourtMicroservice.domain.model.Order;

public interface IOrderServicePort {
    void createOrder(Long userId, Order order);

}
