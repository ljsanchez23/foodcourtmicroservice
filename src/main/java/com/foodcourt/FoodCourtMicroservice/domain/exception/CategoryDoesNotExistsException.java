package com.foodcourt.FoodCourtMicroservice.domain.exception;

public class CategoryDoesNotExistsException extends RuntimeException{
    public CategoryDoesNotExistsException(String message){
        super(message);
    }
}
