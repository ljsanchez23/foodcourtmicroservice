package com.foodcourt.FoodCourtMicroservice.configuration;

import com.foodcourt.FoodCourtMicroservice.configuration.util.ConfigurationConstants;
import com.foodcourt.FoodCourtMicroservice.configuration.util.ErrorResponse;
import com.foodcourt.FoodCourtMicroservice.domain.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidNameException(InvalidNameException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidEinException(InvalidEinException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidPhoneException(InvalidPhoneException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidRoleException(InvalidRoleException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMandatoryFieldException(MandatoryFieldException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        HttpStatus httpStatus = HttpStatus.resolve(ex.getStatusCode().value());
        String reasonPhrase = (httpStatus != null) ? httpStatus.getReasonPhrase() : ConfigurationConstants.UNKNOWN_STATUS_ERROR_MESSAGE;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                reasonPhrase,
                ex.getReason(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(RestaurantAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantAlreadyExistsException(RestaurantAlreadyExistsException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DishAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDishAlreadyExistsException(DishAlreadyExistsException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPriceException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPriceException(InvalidPriceException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DishDoesNotExistsException.class)
    public ResponseEntity<ErrorResponse> handleDishDoesNotExistsException(DishDoesNotExistsException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantNotFoundException(RestaurantNotFoundException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StatusMustBeDifferentException.class)
    public ResponseEntity<ErrorResponse> handleStatusMustBeDifferentException(StatusMustBeDifferentException ex) {
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedUserException(UnauthorizedUserException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderAlreadyInProgressException.class)
    public ResponseEntity<ErrorResponse> handleOrderAlreadyInProgressException(OrderAlreadyInProgressException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryDoesNotExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryDoesNotExistsException(CategoryDoesNotExistsException ex){
        HttpServletRequest request = getCurrentHttpRequest();
        String requestUri = (request != null) ? request.getRequestURI() : ConfigurationConstants.DEFAULT_PATH;

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ConfigurationConstants.BAD_REQUEST,
                ex.getMessage(),
                requestUri
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    private HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return (attributes != null) ? attributes.getRequest() : null;
    }
}
