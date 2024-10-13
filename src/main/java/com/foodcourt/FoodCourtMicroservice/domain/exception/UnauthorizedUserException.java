package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException(String message){
        super(message);
    }
}
