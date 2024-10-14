package com.foodcourt.FoodCourtMicroservice.util;

import com.foodcourt.FoodCourtMicroservice.domain.model.Category;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestDataFactory {
    public static Restaurant createDefaultRestaurant(){
        return new Restaurant(TestConstants.RESTAURANT_ID, TestConstants.RESTAURANT_NAME, TestConstants.RESTAURANT_EIN, TestConstants.RESTAURANT_ADDRESS, TestConstants.RESTAURANT_PHONE, TestConstants.RESTAURANT_URL_LOGO, TestConstants.RESTAURANT_USER_ID, null);
    }
    public static Dish createDefaultDish() {
        return new Dish(
                TestConstants.DISH_ID,
                TestConstants.DISH_NAME,
                TestConstants.DISH_PRICE,
                TestConstants.DISH_DESCRIPTION,
                TestConstants.DISH_URL_LOGO,
                TestConstants.DISH_STATUS,
                TestConstants.CATEGORY_ID,
                TestConstants.DISH_RESTAURANT_ID
        );
    }

    public static Category createDefaultCategory(){
        return new Category(TestConstants.CATEGORY_ID, TestConstants.CATEGORY_NAME, TestConstants.CATEGORY_DESCRIPTION);
    }

    public static Restaurant createRestaurantOwnedBy(Long userId) {
        return new Restaurant(
                TestConstants.RESTAURANT_ID,
                TestConstants.RESTAURANT_NAME,
                TestConstants.RESTAURANT_EIN,
                TestConstants.RESTAURANT_ADDRESS,
                TestConstants.RESTAURANT_PHONE,
                TestConstants.RESTAURANT_URL_LOGO,
                userId,
                null
        );
    }

    public static PagedResult<Restaurant> createPagedRestaurants() {
        List<Restaurant> restaurantList = Arrays.asList(
                createDefaultRestaurant(),
                new Restaurant(2L, "Restaurant 2", 987654321, "456 Main St", "+123456789", "https://example.com/logo2.png", 2L, null)
        );

        return new PagedResult<>(
                restaurantList,
                0,
                10,
                restaurantList.size()
        );
    }

    public static PagedResult<Dish> createPagedResultDishes() {
        List<Dish> dishList = Arrays.asList(
                new Dish(1L, "Dish 1", 100, "Delicious Dish 1", "https://example.com/dish1.png", true, TestConstants.CATEGORY_ID, TestConstants.RESTAURANT_ID),
                new Dish(2L, "Dish 2", 200, "Delicious Dish 2", "https://example.com/dish2.png", true, TestConstants.CATEGORY_ID, TestConstants.RESTAURANT_ID)
        );

        return new PagedResult<>(
                dishList,
                0,
                10,
                dishList.size()
        );
    }

    // Crear una lista de platos sin paginación
    public static List<Dish> createDishList() {
        return Arrays.asList(
                new Dish(1L, "Dish 1", 100, "Delicious Dish 1", "https://example.com/dish1.png", true, TestConstants.CATEGORY_ID, TestConstants.RESTAURANT_ID),
                new Dish(2L, "Dish 2", 200, "Delicious Dish 2", "https://example.com/dish2.png", true, TestConstants.CATEGORY_ID, TestConstants.RESTAURANT_ID)
        );
    }

    // Crear un PagedResult vacío
    public static PagedResult<Dish> createEmptyPagedResultDishes() {
        return new PagedResult<>(
                Collections.emptyList(),
                0,
                10,
                0
        );
    }
}
