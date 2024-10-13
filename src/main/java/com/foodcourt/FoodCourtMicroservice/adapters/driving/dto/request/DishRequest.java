package com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishRequest {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String urlLogo;
    private Boolean status;
    private Long categoryId;
    private Long restaurantId;
}
