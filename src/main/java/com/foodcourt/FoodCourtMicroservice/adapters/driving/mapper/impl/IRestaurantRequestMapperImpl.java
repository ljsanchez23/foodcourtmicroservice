package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.RestaurantRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IRestaurantRequestMapper;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class IRestaurantRequestMapperImpl implements IRestaurantRequestMapper {
    @Override
    public Restaurant toModel(RestaurantRequest restaurantRequest){
        if(restaurantRequest == null){
            return null;
        }
        Long id = null;
        String name = restaurantRequest.getName();
        Integer ein = restaurantRequest.getEin();
        String address = restaurantRequest.getAddress();
        String phone = restaurantRequest.getPhone();
        String urlLogo = restaurantRequest.getUrlLogo();
        Long userId = restaurantRequest.getUserId();

        return new Restaurant(id, name, ein, address, phone, urlLogo, userId, null);
    }
}
