package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.UpdateDishStatusRequest;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDishStatus;

public interface IUpdateDishStatusRequestMapper {
    UpdateDishStatus toModel(UpdateDishStatusRequest updateDishStatusRequest);
}
