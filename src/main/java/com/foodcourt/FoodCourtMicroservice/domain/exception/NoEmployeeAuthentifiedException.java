package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class NoEmployeeAuthentifiedException extends RuntimeException{
    public NoEmployeeAuthentifiedException(String message){
        super(message);
    }
}
