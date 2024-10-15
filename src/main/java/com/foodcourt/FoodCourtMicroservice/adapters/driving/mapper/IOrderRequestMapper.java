package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.OrderRequest;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;

public interface IOrderRequestMapper {
    Order toModel(OrderRequest orderRequest);
}
