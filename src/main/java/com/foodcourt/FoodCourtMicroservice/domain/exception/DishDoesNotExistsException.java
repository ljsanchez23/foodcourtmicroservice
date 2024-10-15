package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class DishDoesNotExistsException extends RuntimeException{
    public DishDoesNotExistsException(String message){
        super(message);
    }
}
