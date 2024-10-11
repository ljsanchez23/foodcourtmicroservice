package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class InvalidEinException extends RuntimeException{
    public InvalidEinException(String message){
        super(message);
    }
}
