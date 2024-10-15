package com.foodcourt.FoodCourtMicroservice.adapters.driving.controller;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.AddEmployeeRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.RestaurantRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.response.RestaurantResponse;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IRestaurantRequestMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.FoodCourtMicroservice.configuration.security.jwt.JwtValidate;
import com.foodcourt.FoodCourtMicroservice.domain.api.IRestaurantServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(AdaptersConstants.RESTAURANT_CONTROLLER_URL)
@RequiredArgsConstructor
public class RestaurantController {
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;

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

    @Operation(summary = AdaptersConstants.GET_ALL_RESTAURANTS_ENDPOINT_SUMMARY, description = AdaptersConstants.GET_ALL_RESTAURANTS_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.OK, description = AdaptersConstants.GET_ALL_RESTAURANTS_OK_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.GET_ALL_RESTAURANTS_BAD_REQUEST_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<PagedResult<RestaurantResponse>> getAllRestaurants(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        PagedResult<Restaurant> restaurants = restaurantServicePort.listRestaurants(page, size);

        List<RestaurantResponse> restaurantResponses = restaurants.getContent().stream()
                .map(restaurant -> new RestaurantResponse(restaurant.getName(), restaurant.getUrlLogo()))
                .toList();

        PagedResult<RestaurantResponse> response = new PagedResult<>(
                restaurantResponses,
                restaurants.getPage(),
                restaurants.getSize(),
                restaurants.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = AdaptersConstants.ADD_EMPLOYEE_ENDPOINT_SUMMARY, description = AdaptersConstants.ADD_EMPLOYEE_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.OK, description = AdaptersConstants.ADD_EMPLOYEE_OK_DESCRIPTION),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.ADD_EMPLOYEE_BAD_REQUEST_DESCRIPTION)
    })
    @PostMapping(AdaptersConstants.ADD_EMPLOYEE_ENDPOINT)
    public ResponseEntity<Void> addEmployeeToRestaurant(
            @RequestBody AddEmployeeRequest addEmployeeRequest) {
        restaurantServicePort.addEmployeeToRestaurant(addEmployeeRequest.getRestaurantId(), addEmployeeRequest.getEmployeeId());
        return ResponseEntity.ok().build();
    }

}
