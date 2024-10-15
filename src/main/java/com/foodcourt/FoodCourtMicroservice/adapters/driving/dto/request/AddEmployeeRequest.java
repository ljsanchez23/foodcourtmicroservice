package com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddEmployeeRequest {
    private Long employeeId;
    private Long restaurantId;
}
