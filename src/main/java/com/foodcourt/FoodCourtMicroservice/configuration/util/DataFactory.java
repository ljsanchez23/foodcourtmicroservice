package com.foodcourt.FoodCourtMicroservice.configuration.util;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;

import java.util.ArrayList;

public class DataFactory {
    public static final Long CATEGORY_ID = 1L;
    public static final String CATEGORY_NAME = "Basic food";
    public static final String CATEGORY_DESCRIPTION = "Food for everyone";

    public static final Long RESTAURANT_ID = 1L;
    public static final String RESTAURANT_NAME = "Admins restaurant";
    public static final Integer EIN = 123456789;
    public static final String ADDRESS = "123 Default Street";
    public static final String PHONE = "+1234567890";
    public static final String URL_LOGO = "https://example.com/logo.png";
    public static final Long USER_ID = 1L;

    public static final Long OWNER_RESTAURANT_ID = 2L;
    public static final String OWNER_RESTAURANT_NAME = "Owners restaurant";
    public static final Integer OWNER_EIN = 224124125;
    public static final String OWNER_ADDRESS = "123 Default Street";
    public static final String OWNER_PHONE = "+1234567890";
    public static final String OWNER_URL_LOGO = "https://example.com/logo.png";
    public static final Long OWNER_USER_ID = 2L;

    public static final RestaurantEntity ADMIN_RESTAURANT = new RestaurantEntity(RESTAURANT_ID, RESTAURANT_NAME, EIN, ADDRESS,
            PHONE, URL_LOGO, USER_ID, null, new ArrayList<>());

    public static final RestaurantEntity OWNER_RESTAURANT = new RestaurantEntity(OWNER_RESTAURANT_ID, OWNER_RESTAURANT_NAME,
            OWNER_EIN, OWNER_ADDRESS, OWNER_PHONE, OWNER_URL_LOGO, OWNER_USER_ID, null, new ArrayList<>());


    public static final CategoryEntity DEFAULT_CATEGORY = new CategoryEntity(CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION);
}
