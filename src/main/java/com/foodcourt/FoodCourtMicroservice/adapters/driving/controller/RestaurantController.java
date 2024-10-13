package com.foodcourt.FoodCourtMicroservice.adapters.driving.controller;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.RestaurantRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IRestaurantRequestMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.FoodCourtMicroservice.configuration.security.jwt.JwtValidate;
import com.foodcourt.FoodCourtMicroservice.domain.api.IRestaurantServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdaptersConstants.RESTAURANT_CONTROLLER_URL)
public class RestaurantController {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;

    public RestaurantController(IRestaurantServicePort restaurantServicePort, IRestaurantRequestMapper restaurantRequestMapper) {
        this.restaurantServicePort = restaurantServicePort;
        this.restaurantRequestMapper = restaurantRequestMapper;
    }

    @Operation(summary = AdaptersConstants.SAVE_RESTAURANT_ENDPOINT_SUMMARY, description = AdaptersConstants.SAVE_RESTAURANT_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.CREATED, description = AdaptersConstants.CREATED_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.BAD_REQUEST_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantRequest restaurantRequest, HttpServletRequest request) {

        Claims claims = JwtValidate.JwtValidation(request);

        Long userId = claims.get(AdaptersConstants.USER_ID_FROM_TOKEN, Long.class);

        Restaurant restaurant = restaurantRequestMapper.toModel(restaurantRequest);

        restaurantServicePort.saveRestaurant(userId, restaurant);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
