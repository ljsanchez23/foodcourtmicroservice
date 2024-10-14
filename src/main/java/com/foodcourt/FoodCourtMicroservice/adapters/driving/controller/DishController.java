package com.foodcourt.FoodCourtMicroservice.adapters.driving.controller;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.DishRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.UpdateDishRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.UpdateDishStatusRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IDishRequestMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IUpdateDishRequestMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IUpdateDishStatusRequestMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.FoodCourtMicroservice.configuration.security.jwt.JwtValidate;
import com.foodcourt.FoodCourtMicroservice.domain.api.IDishServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDishStatus;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdaptersConstants.DISH_CONTROLLER_URL)
public class DishController {
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IUpdateDishRequestMapper updateDishRequestMapper;
    private final IUpdateDishStatusRequestMapper updateDishStatusRequestMapper;

    public DishController(IDishServicePort dishServicePort, IDishRequestMapper dishRequestMapper, IUpdateDishRequestMapper updateDishRequestMapper, IUpdateDishStatusRequestMapper updateDishStatusRequestMapper) {
        this.dishServicePort = dishServicePort;
        this.dishRequestMapper = dishRequestMapper;
        this.updateDishRequestMapper = updateDishRequestMapper;
        this.updateDishStatusRequestMapper = updateDishStatusRequestMapper;
    }

    @Operation(summary = AdaptersConstants.SAVE_DISH_ENDPOINT_SUMMARY, description = AdaptersConstants.SAVE_DISH_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.CREATED, description = AdaptersConstants.DISH_CREATED_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.DISH_BAD_REQUEST_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Void> saveDish(@RequestBody DishRequest dishRequest, HttpServletRequest request) {
        Claims claims = JwtValidate.JwtValidation(request);
        Long userId = claims.get(AdaptersConstants.USER_ID_FROM_TOKEN, Long.class);
        Dish dish = dishRequestMapper.toModel(dishRequest);
        dishServicePort.saveDish(userId, dish);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = AdaptersConstants.UPDATE_DISH_ENDPOINT_SUMMARY, description = AdaptersConstants.UPDATE_DISH_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.UPDATED_NON_CONTENT, description = AdaptersConstants.DISH_UPDATED_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.UPDATE_DISH_BAD_REQUEST_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class)))
    })
    @PutMapping(AdaptersConstants.UPDATE_DISH_ENDPOINT)
    public ResponseEntity<Void> updateDish(@PathVariable Long dishId, @RequestBody UpdateDishRequest updateDishRequest,
                                           HttpServletRequest request) {
        Claims claims = JwtValidate.JwtValidation(request);
        Long userId = claims.get(AdaptersConstants.USER_ID_FROM_TOKEN, Long.class);

        UpdateDish updateDish = updateDishRequestMapper.toModel(updateDishRequest);

        dishServicePort.updateDish(userId, dishId, updateDish);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = AdaptersConstants.UPDATE_DISH_STATUS_ENDPOINT_SUMMARY, description = AdaptersConstants.UPDATE_DISH_STATUS_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.UPDATED_NON_CONTENT, description = AdaptersConstants.DISH_STATUS_UPDATED_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.UPDATE_DISH_STATUS_BAD_REQUEST_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class)))
    })
    @PutMapping(AdaptersConstants.UPDATE_DISH_STATUS_ENDPOINT)
    public ResponseEntity<Void> updateDishStatus(@PathVariable Long dishId, @RequestBody UpdateDishStatusRequest updateDishStatusRequest,
                                                 HttpServletRequest request){
        Claims claims = JwtValidate.JwtValidation(request);
        Long userId = claims.get(AdaptersConstants.USER_ID_FROM_TOKEN, Long.class);

        UpdateDishStatus updateDishStatus = updateDishStatusRequestMapper.toModel(updateDishStatusRequest);

        dishServicePort.updateDishStatus(userId, dishId, updateDishStatus);
        return ResponseEntity.noContent().build();
    }
}
