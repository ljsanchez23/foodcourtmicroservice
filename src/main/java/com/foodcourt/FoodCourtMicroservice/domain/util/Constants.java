package com.foodcourt.FoodCourtMicroservice.domain.util;

import java.util.regex.Pattern;

public class Constants {
    public static final String PHONE_REGEX = "^\\+?[0-9]{1,12}$";
    public static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    public static final int PHONE_MAX_LENGTH = 13;
    public static final String PHONE_UNDER_THIRTEEN_CHARACTERS = "Phone number cannot exceed 13 characters.";
    public static final String INVALID_PHONE_FORMAT = "Invalid phone number. It must contain only numbers and optionally a '+' at the start.";
    public static final String INVALID_EIN_FORMAT = "EIN number must be positive and numeric.";
    public static final String RESTAURANT_NAME_REGEX = "\\d+";
    public static final String RESTAURANT_MUST_BE_ALPHANUMERIC = "Restaurant's name cannot have only numbers";
    public static final String RESTAURANT_NAME_MANDATORY = "Restaurant's name is mandatory";
    public static final String RESTAURANT_EIN_MANDATORY = "Restaurant's EIN is mandatory";
    public static final String RESTAURANT_ADDRESS_MANDATORY = "Restaurant's address is mandatory";
    public static final String RESTAURANT_PHONE_MANDATORY = "Restaurant's phone is mandatory";
    public static final String RESTAURANT_URL_LOGO_MANDATORY = "Restaurant's UrlLogo is mandatory";
    public static final String RESTAURANT_USERID_MANDATORY = "Restaurant's userId is mandatory";
    public static final int EIN_VALIDATOR = 0;
    public static final int PRICE_VALIDATOR = 0;
    public static final String ROLE_OWNER = "ROLE_OWNER";
    public static final String INVALID_ROLE_MESSAGE = "The user has to be the owner of the restaurant.";
    public static final String RESTAURANT_ALREADY_EXISTS = "The restaurant already exists.";
    public static final String RESTAURANT_NAME_MUST_BE_DIFFERENT = "The restaurant's name must be different from others.";
    public static final String PRICE_MUST_BE_POSITIVE = "Price must be positive";
    public static final String DISH_NAME_MANDATORY = "Dish name is mandatory";
    public static final String DISH_PRICE_MANDATORY = "Dish price is mandatory";
    public static final String DISH_DESCRIPTION_MANDATORY = "Dish description is mandatory";
    public static final String DISH_URL_LOGO_MANDATORY = "Dish url logo is mandatory";
    public static final String DISH_CATEGORY_MANDATORY = "Dish category is mandatory";
    public static final String DISH_ALREADY_EXISTS = "Dish already exists in the database";
    public static final String DISH_RESTAURANT_MANDATORY = "Dish restaurant is mandatory";
    public static final String DISH_DOES_NOT_EXISTS = "Dish does not exists in the database";
}
