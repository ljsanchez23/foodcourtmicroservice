package com.foodcourt.FoodCourtMicroservice.domain.util;

import com.foodcourt.FoodCourtMicroservice.domain.exception.*;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;

public class Validator {

    public static void validateRestaurant(Restaurant restaurant){
        validateRestaurantMandatoryFields(restaurant);
        validateRestaurantName(restaurant.getName());
        validatePhone(restaurant.getPhone());
        validateEin(restaurant.getEin());
    }

    public static void validateDish(Dish dish){
        validateDishMandatoryFields(dish);
        validateDishPrice(dish.getPrice());
    }


    private static void validateRestaurantMandatoryFields(Restaurant restaurant){
        checkMandatory(restaurant.getName(), Constants.RESTAURANT_NAME_MANDATORY);
        checkMandatory(restaurant.getEin(), Constants.RESTAURANT_EIN_MANDATORY);
        checkMandatory(restaurant.getAddress(), Constants.RESTAURANT_ADDRESS_MANDATORY);
        checkMandatory(restaurant.getPhone(), Constants.RESTAURANT_PHONE_MANDATORY);
        checkMandatory(restaurant.getUrlLogo(), Constants.RESTAURANT_URL_LOGO_MANDATORY);
        checkMandatory(restaurant.getUserId(), Constants.RESTAURANT_USERID_MANDATORY);
    }

    private static void validateDishMandatoryFields(Dish dish){
        checkMandatory(dish.getName(), Constants.DISH_NAME_MANDATORY);
        checkMandatory(dish.getPrice(), Constants.DISH_PRICE_MANDATORY);
        checkMandatory(dish.getDescription(), Constants.DISH_DESCRIPTION_MANDATORY);
        checkMandatory(dish.getUrlLogo(), Constants.DISH_URL_LOGO_MANDATORY);
        checkMandatory(dish.getCategoryId(), Constants.DISH_CATEGORY_MANDATORY);
        checkMandatory(dish.getRestaurantId(), Constants.DISH_RESTAURANT_MANDATORY);
    }

    private static void checkMandatory(Object field, String errorMessage){
        if(field == null || (field instanceof String str && str.trim().isEmpty())) {
            throw new MandatoryFieldException(errorMessage);
        }
    }

    private static void validatePhone(String phone) {
        if (phone.length() > Constants.PHONE_MAX_LENGTH) {
            throw new InvalidPhoneException((Constants.PHONE_UNDER_THIRTEEN_CHARACTERS));
        }
        if (!Constants.PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidPhoneException(Constants.INVALID_PHONE_FORMAT);
        }
    }

    private static void validateRestaurantName(String name) {
        if (name.matches(Constants.RESTAURANT_NAME_REGEX)) {
            throw new InvalidNameException(Constants.RESTAURANT_MUST_BE_ALPHANUMERIC);
        }
    }
    private static void validateEin(Integer ein) {
        if (ein <= Constants.EIN_VALIDATOR) {
            throw new InvalidEinException(Constants.INVALID_EIN_FORMAT);
        }
    }

    private static void validateDishPrice(Integer price){
        if (price <= Constants.PRICE_VALIDATOR) {
            throw new InvalidPriceException(Constants.PRICE_MUST_BE_POSITIVE);
        }
    }
}
