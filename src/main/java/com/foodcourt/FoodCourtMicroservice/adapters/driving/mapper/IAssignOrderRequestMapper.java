package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.AssignOrderRequest;

public interface IAssignOrderRequestMapper {
    String extractNewStatusFromRequest(AssignOrderRequest assignOrderRequest);
}
