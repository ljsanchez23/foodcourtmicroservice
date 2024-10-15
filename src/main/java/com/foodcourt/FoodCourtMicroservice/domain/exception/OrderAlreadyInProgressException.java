package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class OrderAlreadyInProgressException extends RuntimeException{
    public OrderAlreadyInProgressException(String message){
        super(message);
    }
}
