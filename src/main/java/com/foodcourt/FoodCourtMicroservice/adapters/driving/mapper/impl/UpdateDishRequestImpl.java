package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.UpdateDishRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IUpdateDishRequestMapper;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;
import org.springframework.stereotype.Component;

@Component
public class UpdateDishRequestImpl implements IUpdateDishRequestMapper {
    @Override
    public UpdateDish toModel(UpdateDishRequest updateDishRequest) {
        if(updateDishRequest == null){
            return null;
        }
        Integer price = updateDishRequest.getPrice();
        String description = updateDishRequest.getDescription();

        return new UpdateDish(price, description);
    }
}
