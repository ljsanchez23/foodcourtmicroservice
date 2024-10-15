package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;

public interface IOrderEntityMapper {
    OrderEntity toEntity(Order order);
    Order toModel(OrderEntity orderEntity);
}
