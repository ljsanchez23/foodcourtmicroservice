package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IOrderEntityMapper;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class IOrderEntityMapperImpl implements IOrderEntityMapper {
    @Override
    public OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());

        if (order.getRestaurantId() != null) {
            RestaurantEntity restaurantEntity = new RestaurantEntity();
            restaurantEntity.setId(order.getRestaurantId());
            orderEntity.setRestaurant(restaurantEntity);
        }

        List<OrderDishEntity> orderDishEntities = order.getDishQuantities().entrySet().stream()
                .map(entry -> {
                    OrderDishEntity orderDishEntity = new OrderDishEntity();
                    DishEntity dishEntity = new DishEntity();
                    dishEntity.setId(entry.getKey());
                    orderDishEntity.setDish(dishEntity);
                    orderDishEntity.setQuantity(entry.getValue());
                    orderDishEntity.setOrder(orderEntity);
                    return orderDishEntity;
                }).toList();

        orderEntity.setOrderDishes(orderDishEntities);

        orderEntity.setStatus(order.getStatus());
        orderEntity.setCustomerId(order.getCustomerId());

        return orderEntity;
    }

    @Override
    public Order toModel(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        Long restaurantId = (orderEntity.getRestaurant() != null) ? orderEntity.getRestaurant().getId() : null;

        Map<Long, Integer> dishQuantities = orderEntity.getOrderDishes().stream()
                .collect(Collectors.toMap(
                        orderDishEntity -> orderDishEntity.getDish().getId(),
                        OrderDishEntity::getQuantity
                ));

        List<Dish> dishes = orderEntity.getOrderDishes().stream()
                .map(orderDishEntity -> {
                    Dish dish = new Dish();
                    dish.setId(orderDishEntity.getDish().getId());
                    return dish;
                }).toList();

        return new Order(
                orderEntity.getId(),
                restaurantId,
                dishes,
                dishQuantities,
                orderEntity.getStatus(),
                orderEntity.getCustomerId()
        );
    }
}

