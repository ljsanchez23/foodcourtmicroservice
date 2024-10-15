package com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.AssignOrderRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IAssignOrderRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class IAssignOrderRequestMapperImpl implements IAssignOrderRequestMapper {
    @Override
    public String extractNewStatusFromRequest(AssignOrderRequest assignOrderRequest) {
        return assignOrderRequest.getStatus();
    }
}
