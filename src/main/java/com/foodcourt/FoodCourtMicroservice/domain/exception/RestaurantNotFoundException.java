package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(String message){
        super(message);
    }
}
