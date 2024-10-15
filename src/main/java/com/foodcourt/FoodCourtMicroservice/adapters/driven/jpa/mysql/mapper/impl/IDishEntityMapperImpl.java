package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IDishEntityMapper;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import org.springframework.stereotype.Component;

@Component
public class IDishEntityMapperImpl implements IDishEntityMapper {
    @Override
    public DishEntity toEntity(Dish dish) {
        if(dish == null){
            return null;
        }

        DishEntity dishEntity = new DishEntity();

        dishEntity.setId(dish.getId());
        dishEntity.setName(dish.getName());
        dishEntity.setPrice(dish.getPrice());
        dishEntity.setDescription(dish.getDescription());
        dishEntity.setUrlLogo(dish.getUrlLogo());
        dishEntity.setStatus(dish.getStatus());
        if(dish.getCategoryId() != null){
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(dish.getCategoryId());
            dishEntity.setCategory(categoryEntity);
        } else {
            dishEntity.setCategory(null);
        }
        if (dish.getRestaurantId() != null) {
            RestaurantEntity restaurantEntity = new RestaurantEntity();
            restaurantEntity.setId(dish.getRestaurantId());
            dishEntity.setRestaurant(restaurantEntity);
        } else {
            dishEntity.setRestaurant(null);
        }

        return dishEntity;
    }

    @Override
    public Dish toModel(DishEntity dishEntity) {
        if (dishEntity == null) {
            return null;
        }

        Dish dish = new Dish();

        dish.setId(dishEntity.getId());
        dish.setName(dishEntity.getName());
        dish.setPrice(dishEntity.getPrice());
        dish.setDescription(dishEntity.getDescription());
        dish.setUrlLogo(dishEntity.getUrlLogo());
        dish.setStatus(dishEntity.getStatus());

        if (dishEntity.getCategory() != null) {
            dish.setCategoryId(dishEntity.getCategory().getId());
        } else {
            dish.setCategoryId(null);
        }

        if (dishEntity.getRestaurant() != null) {
            dish.setRestaurantId(dishEntity.getRestaurant().getId());
        } else {
            dish.setRestaurantId(null);
        }

        return dish;
    }
}
