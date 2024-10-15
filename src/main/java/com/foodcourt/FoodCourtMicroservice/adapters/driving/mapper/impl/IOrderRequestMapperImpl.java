package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.OrderRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IOrderRequestMapper;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import org.springframework.stereotype.Component;


@Component
public class IOrderRequestMapperImpl implements IOrderRequestMapper {

    @Override
    public Order toModel(OrderRequest orderRequest) {
        if (orderRequest == null) {
            return null;
        }

        return new Order(
                null,
                orderRequest.getRestaurantId(),
                orderRequest.getDishIds().stream().map(dishId -> {
                    Dish dish = new Dish();
                    dish.setId(dishId);
                    return dish;
                }).toList(),
                orderRequest.getDishQuantities(),
                Constants.PENDING_STATUS,
                null,
                null
        );
    }
}
