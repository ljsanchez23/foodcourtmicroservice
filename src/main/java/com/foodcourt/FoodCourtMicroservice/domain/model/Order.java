package com.foodcourt.FoodCourtMicroservice.domain.model;

import java.util.List;
import java.util.Map;

public class Order {
    private Long id;
    private Long restaurantId;
    private List<Dish> dishes;
    private Map<Long, Integer> dishQuantities;
    private String status;
    private Long customerId;

    public Order(Long id, Long restaurantId, List<Dish> dishes, Map<Long, Integer> dishQuantities, String status, Long customerId) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.dishes = dishes;
        this.dishQuantities = dishQuantities;
        this.status = status;
        this.customerId = customerId;
    }

    public Order(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Map<Long, Integer> getDishQuantities() {
        return dishQuantities;
    }

    public void setDishQuantities(Map<Long, Integer> dishQuantities) {
        this.dishQuantities = dishQuantities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
