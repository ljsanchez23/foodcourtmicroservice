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
    public static final String GET_ALL_DISHES_ENDPOINT = "/restaurant/{restaurantId}";
    public static final String GET_ALL_DISHES_ENDPOINT_SUMMARY = "Get all dishes";

    public static final String GET_ALL_DISHES_ENDPOINT_DESCRIPTION = "This endpoint can be used to get all the dishes";
    public static final String GET_ALL_DISHES_OK_DESCRIPTION = "If you get the dishes you'll receive a 200 response code";
    public static final String GET_ALL_DISHES_BAD_REQUEST_DESCRIPTION = "If you don't get the dishes you'll receive a 400 response" +
            "code";
    public static final String ORDER_RESTAURANT_JOIN_COLUMN = "restaurant_id";
    public static final String ORDER_TABLE_NAME = "customer_order";
    public static final String ORDER_DISHES_JOIN_COLUMN = "dish_id";
    public static final String ORDER_CONTROLLER_URL = "/order";
    public static final String ORDER_JOIN_COLUMN = "order_id";
    public static final String DISH_JOIN_COLUMN = "dish_id";
    public static final String ORDER_DISH_TABLE_NAME = "order_dish";
    public static final String CREATE_ORDER_ENDPOINT_SUMMARY = "This endpoint is used to create the order";
    public static final String CREATE_ORDER_ENDPOINT_DESCRIPTION = "When a customer wants to create an order they" +
            "have to use this endpoint";
    public static final String CREATE_ORDER_CREATED_DESCRIPTION = "If the order is created a 204 response will be given";
    public static final String CREATE_ORDER_BAD_REQUEST_DESCRIPTION = "If the order is not created a 400 response will be" +
            "given";
    public static final String ORDER_MAPPED_BY = "order";
    public static final String ADD_EMPLOYEE_ENDPOINT = "/addEmployee";
    public static final String ADD_EMPLOYEE_ENDPOINT_SUMMARY = "This endpoint is used to add an employee to the restaurant";
    public static final String ADD_EMPLOYEE_ENDPOINT_DESCRIPTION = "This endpoint receives the information of the user when its" +
            "created, adding the employee to the restaurant";
    public static final String ADD_EMPLOYEE_OK_DESCRIPTION = "If the employee is added, a 200 response code will be given";
    public static final String ADD_EMPLOYEE_BAD_REQUEST_DESCRIPTION = "If the employee is not added, a 400 response code will be" +
            "given";
    public static final String UNAUTHORIZED = "401";
    public static final String LIST_ORDERS_ENDPOINT_SUMMARY = "This endpoint is used to list the orders";
    public static final String LIST_ORDERS_ENDPOINT_DESCRIPTION = "The employees can use this endpoint to access the list of the " +
            "orders, they can sort as well depending on the status";
    public static final String LIST_ORDERS_OK_DESCRIPTION = "If the orders are listed a 200 response code will be given";
    public static final String LIST_ORDERS_BAD_REQUEST_DESCRIPTION = "If the orders are not listed a 400 response code will be given";
    public static final String LIST_ORDER_UNAUTHORIZED_DESCRIPTION = "If the authorization does not goes through a 401 response code" +
            "will be given";
    public static final String PAGE_PARAM_VALUE = "page";
    public static final String SIZE_PARAM_VALUE = "size";
    public static final String STATUS_PARAM_VALUE = "status";
    public static final String SAVE_EMPLOYEE_SQL_QUERY = "INSERT INTO restaurant_employees (restaurant_id, employee_id) VALUES (:restaurantId, :employeeId)";
    public static final String RESTAURANT_ID = "restaurantId";
    public static final String EMPLOYEE_ID = "employeeId";
    public static final String RESTAURANT_ID_COLUMN_NAME = "restaurant_id";
    public static final String EMPLOYEE_ID_COLUMN_NAME = "employee_id";
    public static final String RESTAURANT_EMPLOYEE_JOIN_TABLE = "restaurant_employees";
    public static final String IS_EMPLOYEE_ASSIGNED_SQL_QUERY = "SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
            "FROM RestaurantEntity r JOIN r.employeesId e " +
            "WHERE r.id = :restaurantId AND e = :employeeId";
    public static final String ASSIGN_ORDER_ENDPOINT_URL = "assign/{orderId}";
}
