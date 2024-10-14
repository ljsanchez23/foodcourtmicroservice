package com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantResponse {
    private String name;
    private String urlLogo;
}
