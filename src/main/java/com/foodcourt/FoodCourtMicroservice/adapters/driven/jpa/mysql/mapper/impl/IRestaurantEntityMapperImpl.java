package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class IRestaurantEntityMapperImpl implements IRestaurantEntityMapper {

    @Override
    public RestaurantEntity toEntity(Restaurant restaurant){
        if(restaurant == null){
            return null;
        }
        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setName(restaurant.getName());
        restaurantEntity.setEin(restaurant.getEin());
        restaurantEntity.setAddress(restaurant.getAddress());
        restaurantEntity.setPhone(restaurant.getPhone());
        restaurantEntity.setUrlLogo(restaurant.getUrlLogo());
        restaurantEntity.setUserId(restaurant.getUserId());

        return restaurantEntity;
    }

    @Override
    public Restaurant toModel(RestaurantEntity restaurantEntity){
        Long id = restaurantEntity.getId();
        String name = restaurantEntity.getName();
        Integer ein = restaurantEntity.getEin();
        String address = restaurantEntity.getAddress();
        String phone = restaurantEntity.getPhone();
        String urlLogo = restaurantEntity.getUrlLogo();
        Long userId = restaurantEntity.getUserId();

        return new Restaurant(id, name, ein, address, phone, urlLogo, userId, null);
    }
}
