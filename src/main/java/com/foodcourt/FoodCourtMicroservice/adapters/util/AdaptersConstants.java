package com.foodcourt.FoodCourtMicroservice.adapters.util;

public class AdaptersConstants {
    public static final String USER_CLIENT_NAME = "UserMicroservice";
    public static final String USER_CLIENT_URL = "localhost:8080";
    public static final String GET_ROLE_BY_ID_URL = "/user/{id}";
    public static final String RESTAURANT_TABLE_NAME = "restaurant";
    public static final String RESTAURANT_CONTROLLER_URL = "/restaurant";

    public static final String CREATED = "201";
    public static final String BAD_REQUEST = "400";
    public static final String JSON = "application/json";
    public static final String CREATED_DESCRIPTION = "The restaurant has been successfully created";
    public static final String BAD_REQUEST_DESCRIPTION = "The restaurant has not been successfully created";
    public static final String SAVE_RESTAURANT_ENDPOINT_SUMMARY = "Save the restaurant in the database";
    public static final String SAVE_RESTAURANT_ENDPOINT_DESCRIPTION = "This endpoint saves the restaurant in the database";
}
