package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.UpdateDishStatusRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IUpdateDishStatusRequestMapper;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDishStatus;
import org.springframework.stereotype.Component;

@Component
public class IUpdateDishStatusRequestMapperImpl implements IUpdateDishStatusRequestMapper {
    @Override
    public UpdateDishStatus toModel(UpdateDishStatusRequest updateDishStatusRequest) {
        if(updateDishStatusRequest == null){
            return null;
        }
        Boolean status = updateDishStatusRequest.isStatus();

        return new UpdateDishStatus(status);
    }
}
