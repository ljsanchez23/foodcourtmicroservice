package com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantRequest {
    private String name;
    private Integer ein;
    private String address;
    private String phone;
    private String urlLogo;
    private Long userId;
}
