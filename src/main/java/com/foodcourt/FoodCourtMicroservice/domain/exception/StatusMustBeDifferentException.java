package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class StatusMustBeDifferentException extends RuntimeException{
    public StatusMustBeDifferentException(String message){
        super(message);
    }
}
