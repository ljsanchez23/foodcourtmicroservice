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
}
