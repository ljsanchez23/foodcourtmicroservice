package com.foodcourt.FoodCourtMicroservice.adapters.driving.controller;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.DishRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IDishRequestMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.FoodCourtMicroservice.domain.api.IDishServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdaptersConstants.DISH_CONTROLLER_URL)
public class DishController {
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;

    public DishController(IDishServicePort dishServicePort, IDishRequestMapper dishRequestMapper) {
        this.dishServicePort = dishServicePort;
        this.dishRequestMapper = dishRequestMapper;
    }
    @Operation(summary = AdaptersConstants.SAVE_DISH_ENDPOINT_SUMMARY, description = AdaptersConstants.SAVE_DISH_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.CREATED, description = AdaptersConstants.DISH_CREATED_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.DISH_BAD_REQUEST_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Void> saveDish(@RequestBody DishRequest dishRequest){
        dishServicePort.saveDish(dishRequestMapper.toModel(dishRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
