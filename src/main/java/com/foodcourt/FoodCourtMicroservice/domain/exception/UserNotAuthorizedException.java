package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class UserNotAuthorizedException extends RuntimeException{
    public UserNotAuthorizedException(String message){
        super(message);
    }
}
