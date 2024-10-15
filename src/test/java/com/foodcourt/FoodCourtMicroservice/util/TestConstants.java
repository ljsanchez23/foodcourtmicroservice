package com.foodcourt.FoodCourtMicroservice.util;

public class TestConstants {

    public static final Integer VALID_EIN = 11111;
    public static final String VALID_RESTAURANT_NAME = "The last bite";
    public static final String INVALID_ROLE_EXCEPTION = "The user has not a valid role";
    public static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    public static final String SHOULD_SAVE_RESTAURANT = "Should save the restaurant";
    public static final String RESTAURANT_ALREADY_EXISTS_EXCEPTION_EIN = "Restaurant EIN already exists in the database";
    public static final String RESTAURANT_ALREADY_EXISTS_EXCEPTION_NAME = "Restaurant name already exists in the database";


    public static final String PHONE_MAX_LENGTH_EXCEPTION = "Should throw InvalidPhoneException when phone exceeds max length";
    public static final String SHOULD_NOT_THROW_EXCEPTION = "Should not throw any exception when all fields are valid";

    public static final String MANDATORY_FIELD_EXCEPTION = "Mandatory field exception";
    public static final String INVALID_PHONE_FORMAT_EXCEPTION = "Invalid phone format exception";
    public static final String INVALID_EIN_EXCEPTION = "Invalid EIN exception";
    public static final String INVALID_NAME_EXCEPTION = "Invalid name exception";

    public static final Long RESTAURANT_ID = 1L;
    public static final String RESTAURANT_NAME = "The last bite";
    public static final Integer RESTAURANT_EIN = 11111;
    public static final String RESTAURANT_ADDRESS = "Your last street";
    public static final String RESTAURANT_PHONE = "32222222";
    public static final String RESTAURANT_URL_LOGO = "https://defaulturl.logo/test";
    public static final Long RESTAURANT_USER_ID = 1L;
    public static final String EMPTY_FIELD = "";
    public static final String LONG_PHONE = "12345678901234";
    public static final String INVALID_PHONE = "123-abc-xyz";
    public static final int INVALID_EIN = -1;
    public static final String INVALID_NAME = "11111111111";

    public static final Long DISH_ID = 1L;
    public static final String DISH_NAME = "Pasta Bolognese";
    public static final Integer DISH_PRICE = 15;
    public static final String DISH_DESCRIPTION = "Delicious pasta with Bolognese sauce";
    public static final String DISH_URL_LOGO = "https://example.com/images/pasta-bolognese.jpg";
    public static final Boolean DISH_STATUS = true;
    public static final Long CATEGORY_ID = 1L;
    public static final Long DISH_RESTAURANT_ID = 1L;

    public static final String CATEGORY_NAME = "Testing";
    public static final String CATEGORY_DESCRIPTION = "This is for testing";
    public static final Integer INVALID_PRICE = -1;
    public static final String INVALID_PRICE_EXCEPTION = "Invalid price exception";
    public static final String DEFAULT_DISH_NAME = "Pasta Bolognese";
    public static final String SHOULD_SAVE_DISH = "Should save dish";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_DISH_EXISTS = "Should throw exception when dish exists";
    public static final String SHOULD_SET_STATUS = "Should set status true when null is false";
    public static final String DISH_STATUS_MESSAGE = "The status should be set to true if it's null";
    public static final String SHOULD_SAVE_CATEGORY = "Should save category";
    public static final String SHOULD_NOT_SAVE_CATEGORY = "Should not save category";
    public static final String SHOULD_UPDATE_DISH_PRICE_AND_DESCRIPTION = "Should update dish price and description";
    public static final Integer DISH_RANDOM_PRICE = 20;
    public static final Long USER_ID = 1L;
    public static final String SHOULD_THROW_EXCEPTION_WHEN_NOT_RESTAURANT_OWNER = "Should throw exception when the user" +
            "is not the restaurant owner.";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_UPDATING_DISH_NOT_OWNER = "Should throw exception when the " +
            "dish is not updated by the owner";
    public static final Integer DISH_NEW_PRICE = 30;
    public static final String DISH_NEW_DESCRIPTION = "New dish description";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_DISH_NOT_FOUND = "Dish not found exception";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_USER_NOT_OWNER = "User is not the owner";
    public static final Long DIFFERENT_USER_ID = 5L;
    public static final String SHOULD_NOT_UPDATE_DISH_WHEN_NO_CHANGES = "Should not update when no changes";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_UNAUTHORIZED_USER = "Unauthorized user";
    public static final String SHOULD_SAVE_RESTAURANT_WHEN_VALIDATIONS_PASS = "Should save restaurant";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_RESTAURANT_EXISTS_BY_EIN = "Should throw exception restaurant " +
            "already exists";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_RESTAURANT_EXISTS_BY_NAME = "Should throw exception when the " +
            "name of the restaurant already exists";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_USER_IS_NOT_OWNER = "Should throw exception when the user is not" +
            "the owner";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_USER_NOT_AUTHORIZED = "Unauthorized user exception";
    public static final String SHOULD_SAVE_CATEGORY_WHEN_VALIDATIONS_PASS = "Should save category";
    public static final String SHOULD_NOT_SAVE_CATEGORY_WHEN_ALREADY_EXISTS = "Should not save category because it already" +
            "exists";
    public static final String SHOULD_NOT_THROW_EXCEPTION_ON_VALID_DISH = "Should not thrown exception on valid dish";
    public static final String SHOULD_UPDATE_STATUS_SUCCESSFULLY = "Should update the status successfully";
    public static final Long DIFFERENT_RESTAURANT_ID = 6L;
    public static final String SHOULD_THROW_EXCEPTION_WHEN_DISH_IS_FROM_OTHER_RESTAURANT = "Should throw exception when dish belongs to a different restaurant";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_STATUS_IS_THE_SAME = "Should throw exception when the new status is the same as the current status";
    public static final String SHOULD_LIST_RESTAURANTS = "Should list restaurants with default pagination when page and size are null";
    public static final String SHOULD_LIST_DISHES_BY_RESTAURANT = "Should list dishes by restaurant without category filter";
    public static final String SHOULD_LIST_DISHES_FILTERED = "Should list dishes by restaurant with category filter";
    public static final String SHOULD_THROW_EXCEPTION_CATEGORY_DOES_NOT_EXISTS = "Should throw CategoryDoesNotExistsException when category does not exist";
    public static final Long EMPLOYEE_ID = 4L;
    public static final String SHOULD_SAVE_EMPLOYEE_WITH_RIGHT_PARAMS = "This method should save the employee when the params are correct";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_EMPLOYEE_ASSIGNED = "Should throw exception when employee is already" +
            "assigned";
    public static final String COMPLETED_STATUS = "COMPLETED";
    public static final Long TOTAL_ELEMENTS = 0L;
    public static final String SHOULD_RETURN_ORDERS_PAGE_RESULT = "Should return orders page result";
    public static final String SHOULD_THROW_EXCEPTION_WHEN_ORDER_ALREADY_IN_PROGRESS = "Should throw exception when order already" +
            "in progress";
    public static final String SHOULD_SAVE_ORDER_WHEN_NO_ORDER_IS_IN_PROGRESS = "Should save order when no orders are in progress";
    public static final Long ORDER_ID = 1L;
    public static final String ASSIGNED_STATUS = "assigned";
    public static final String SHOULD_THROWN_EXCEPTION_WHEN_ORDER_DOES_NOT_EXISTS = "Should throw exception when order does not exist";
    public static final String SHOULD_THROWN_EXCEPTION_WHEN_USER_IS_NOT_AUTHENTICATED = "Should throw exception when user is not authenticated";
    public static final String SHOULD_THROWN_EXCEPTION_WHEN_USER_IS_NOT_A_RESTAURANT_EMPLOYEE = "Should throw exception when user is not an employee of the restaurant";
    public static final String SHOULD_ASSIGN_ORDER_WHEN_VALIDATION_PASS = "Should assign order to user when all validations pass";
}
