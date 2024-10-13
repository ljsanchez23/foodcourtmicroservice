package com.foodcourt.FoodCourtMicroservice.domain.util;
import com.foodcourt.FoodCourtMicroservice.domain.exception.*;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.util.TestConstants;
import com.foodcourt.FoodCourtMicroservice.util.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
class ValidatorTest {

    private Restaurant validRestaurant;
    private Dish validDish;

    @BeforeEach
    void setUp() {
        validRestaurant = TestDataFactory.createDefaultRestaurant();
        validDish = TestDataFactory.createDefaultDish();
    }

    @Test
    @DisplayName(TestConstants.MANDATORY_FIELD_EXCEPTION)
    void shouldThrowMandatoryFieldExceptionWhenFieldIsNull() {
        validRestaurant.setName(null);

        assertThrows(MandatoryFieldException.class,
                () -> Validator.validateRestaurant(validRestaurant));
    }

    @Test
    @DisplayName(TestConstants.MANDATORY_FIELD_EXCEPTION)
    void shouldThrowMandatoryFieldExceptionWhenFieldIsEmpty() {
        validRestaurant.setName(TestConstants.EMPTY_FIELD);

        assertThrows(MandatoryFieldException.class,
                () -> Validator.validateRestaurant(validRestaurant));
    }

    @Test
    @DisplayName(TestConstants.PHONE_MAX_LENGTH_EXCEPTION)
    void shouldThrowInvalidPhoneExceptionWhenPhoneExceedsMaxLength() {
        validRestaurant.setPhone(TestConstants.LONG_PHONE);

        assertThrows(InvalidPhoneException.class,
                () -> Validator.validateRestaurant(validRestaurant));
    }

    @Test
    @DisplayName(TestConstants.INVALID_PHONE_FORMAT_EXCEPTION)
    void shouldThrowInvalidPhoneExceptionWhenPhoneFormatIsInvalid() {
        validRestaurant.setPhone(TestConstants.INVALID_PHONE);

        assertThrows(InvalidPhoneException.class,
                () -> Validator.validateRestaurant(validRestaurant));
    }

    @Test
    @DisplayName(TestConstants.INVALID_NAME_EXCEPTION)
    void shouldThrowInvalidNameExceptionWhenRestaurantNameIsNotAlphanumeric() {
        validRestaurant.setName(TestConstants.INVALID_NAME);

        assertThrows(InvalidNameException.class,
                () -> Validator.validateRestaurant(validRestaurant));
    }

    @Test
    @DisplayName(TestConstants.INVALID_EIN_EXCEPTION)
    void shouldThrowInvalidEinExceptionWhenEinIsInvalid() {
        validRestaurant.setEin(TestConstants.INVALID_EIN);

        assertThrows(InvalidEinException.class,
                () -> Validator.validateRestaurant(validRestaurant));
    }

    @Test
    @DisplayName(TestConstants.SHOULD_NOT_THROW_EXCEPTION)
    void shouldNotThrowExceptionWhenAllFieldsAreValid() {
        assertDoesNotThrow(() -> Validator.validateRestaurant(validRestaurant));
    }

    @Test
    @DisplayName(TestConstants.INVALID_PRICE_EXCEPTION)
    void shouldThrowExceptionWhenPriceIsInvalid(){
        validDish.setPrice(TestConstants.INVALID_PRICE);

        assertThrows(InvalidPriceException.class,
                () -> Validator.validateDish(validDish));
    }

}
