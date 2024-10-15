package com.foodcourt.FoodCourtMicroservice.domain.spi;

import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;

import java.util.List;

public interface IOrderPersistencePort {
    void saveOrder(Order order);
    Boolean existsByCustomerIdAndStatusIn(Long customerId, List<String> statuses);
    PagedResult<Order> findOrdersByRestaurantIdAndStatus(Long id, String status, Integer page, Integer size);
}
