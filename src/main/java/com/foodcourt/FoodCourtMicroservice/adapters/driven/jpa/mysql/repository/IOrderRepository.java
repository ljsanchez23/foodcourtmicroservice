package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    Boolean existsByCustomerIdAndStatusIn(Long customerId, List<String> statuses);
}
