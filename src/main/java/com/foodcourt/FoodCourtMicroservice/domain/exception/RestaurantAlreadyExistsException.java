package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class RestaurantAlreadyExistsException extends RuntimeException{
    public RestaurantAlreadyExistsException(String message){
        super(message);
    }
}
