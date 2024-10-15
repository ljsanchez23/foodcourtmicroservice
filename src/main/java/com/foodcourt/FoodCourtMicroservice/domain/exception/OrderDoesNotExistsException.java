package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class OrderDoesNotExistsException extends RuntimeException{
    public OrderDoesNotExistsException(String message){
        super(message);
    }
}
