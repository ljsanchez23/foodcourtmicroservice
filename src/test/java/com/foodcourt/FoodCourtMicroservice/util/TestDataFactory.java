package com.foodcourt.FoodCourtMicroservice.util;

import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;

public class TestDataFactory {
    public static Restaurant createDefaultRestaurant(){
        return new Restaurant(TestConstants.RESTAURANT_ID, TestConstants.RESTAURANT_NAME, TestConstants.RESTAURANT_EIN, TestConstants.RESTAURANT_ADDRESS, TestConstants.RESTAURANT_PHONE, TestConstants.RESTAURANT_URL_LOGO, TestConstants.RESTAURANT_USER_ID);
    }
}
