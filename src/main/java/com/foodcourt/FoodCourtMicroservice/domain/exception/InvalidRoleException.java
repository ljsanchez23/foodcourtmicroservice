package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class InvalidRoleException extends RuntimeException{
    public InvalidRoleException(String message){
        super(message);
    }
}
