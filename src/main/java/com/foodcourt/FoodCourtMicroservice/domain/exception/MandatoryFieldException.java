package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class MandatoryFieldException extends RuntimeException{
    public MandatoryFieldException(String message){
        super(message);
    }
}
