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
    public static final String JWT_MISSING = "JWT token is missing on the request";
    public static final String USER_ID_FROM_TOKEN = "userId";
    public static final String UPDATE_DISH_STATUS_ENDPOINT = "/{dishId}/status";
    public static final String UPDATE_DISH_STATUS_ENDPOINT_SUMMARY = "Update dish status endpoint";
    public static final String UPDATE_DISH_STATUS_ENDPOINT_DESCRIPTION = "This endpoint can be used to update the current" +
            "status of a dish";
    public static final String DISH_STATUS_UPDATED_DESCRIPTION = "If the status is updated you'll receive a 204 response" +
            "code";
    public static final String UPDATE_DISH_STATUS_BAD_REQUEST_DESCRIPTION = "If the status is not updated a 400 will be given";
    public static final String OK = "200";
    public static final String GET_ALL_RESTAURANTS_ENDPOINT_SUMMARY = "Endpoint to get a paginated list of restaurants";
    public static final String GET_ALL_RESTAURANTS_ENDPOINT_DESCRIPTION = "Through this endpoint you'll see all the restaurants";
    public static final String GET_ALL_RESTAURANTS_OK_DESCRIPTION = "If the request goes through you'll receive a 200 " +
            "response code";
    public static final String GET_ALL_RESTAURANTS_BAD_REQUEST_DESCRIPTION = "If the request does not go through you'll receive a" +
            "400";
}
