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
    public static final String CATEGORY_TABLE_NAME = "category";
    public static final String DISH_TABLE_NAME = "dish";
    public static final String DISH_CATEGORY_JOIN_COLUMN = "category_id";
    public static final String DISH_CONTROLLER_URL = "/dish";
    public static final String RESTAURANT_DISH_RELATION = "restaurant";
    public static final String DISH_RESTAURANT_JOIN_COLUMN = "restaurant_id";

    public static final String DISH_CREATED_DESCRIPTION = "The dish has been successfully created";
    public static final String DISH_BAD_REQUEST_DESCRIPTION = "The dish has not been successfully created";
    public static final String SAVE_DISH_ENDPOINT_SUMMARY = "Save the dish in the database";
    public static final String SAVE_DISH_ENDPOINT_DESCRIPTION = "This endpoint saves the dish in the database";
    public static final String UPDATE_DISH_ENDPOINT = "/{dishId}";
    public static final String UPDATE_DISH_ENDPOINT_SUMMARY = "Updates the dish in the database";
    public static final String UPDATED_NON_CONTENT = "204";
    public static final String UPDATE_DISH_ENDPOINT_DESCRIPTION = "This endpoint is used to update the dish";
    public static final String DISH_UPDATED_DESCRIPTION = "The dish has been updated";
    public static final String UPDATE_DISH_BAD_REQUEST_DESCRIPTION = "When the dish is updated we receive a 204 response";
}
