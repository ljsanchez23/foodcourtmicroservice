package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.DishEntity;

import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;

public interface IDishEntityMapper {
    DishEntity toEntity(Dish dish);
    Dish toModel(DishEntity dishEntity);
}
