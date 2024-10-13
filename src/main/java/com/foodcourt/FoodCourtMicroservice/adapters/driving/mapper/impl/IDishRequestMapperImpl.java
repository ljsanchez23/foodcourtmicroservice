package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.DishRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IDishRequestMapper;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import org.springframework.stereotype.Component;


@Component
public class IDishRequestMapperImpl implements IDishRequestMapper {
    @Override
    public Dish toModel(DishRequest dishRequest) {
        if(dishRequest == null){
            return null;
        }
        Long id = dishRequest.getId();
        String name = dishRequest.getName();
        Integer price = dishRequest.getPrice();
        String description = dishRequest.getDescription();
        String urlLogo = dishRequest.getUrlLogo();
        Long categoryId = dishRequest.getCategoryId();
        Long restaurantId = dishRequest.getRestaurantId();
        Boolean status = dishRequest.getStatus();

        Dish dish = new Dish();
        dish.setId(id);
        dish.setName(name);
        dish.setPrice(price);
        dish.setDescription(description);
        dish.setUrlLogo(urlLogo);
        dish.setCategoryId(categoryId);
        dish.setRestaurantId(restaurantId);
        dish.setStatus(status);

        return dish;
    }
}
