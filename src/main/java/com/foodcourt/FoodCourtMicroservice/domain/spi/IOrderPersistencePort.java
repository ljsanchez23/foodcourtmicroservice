package com.foodcourt.FoodCourtMicroservice.domain.spi;

import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;

import java.util.List;
import java.util.Optional;

public interface IOrderPersistencePort {
    void saveOrder(Order order);
    Boolean existsByCustomerIdAndStatusIn(Long customerId, List<String> statuses);
    PagedResult<Order> findOrdersByRestaurantIdAndStatus(Long id, String status, Integer page, Integer size);
    void assignOrder(Long orderId, Long userId, String status);
    Boolean existsById(Long id);
    Optional<Order> findById(Long id);
}
