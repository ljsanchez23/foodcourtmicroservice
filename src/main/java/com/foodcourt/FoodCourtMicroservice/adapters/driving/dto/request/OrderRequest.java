package com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private Long restaurantId;
    private List<Long> dishIds;
    private Map<Long, Integer> dishQuantities;
}
