package com.foodcourt.FoodCourtMicroservice.adapters.driving.controller;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.OrderRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IOrderRequestMapper;
import com.foodcourt.FoodCourtMicroservice.configuration.security.jwt.JwtValidate;
import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.FoodCourtMicroservice.domain.api.IOrderServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AdaptersConstants.ORDER_CONTROLLER_URL)
@AllArgsConstructor
public class OrderController {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;

    @Operation(summary = AdaptersConstants.CREATE_ORDER_ENDPOINT_SUMMARY, description = AdaptersConstants.CREATE_ORDER_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.CREATED, description = AdaptersConstants.CREATE_ORDER_CREATED_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.CREATE_ORDER_BAD_REQUEST_DESCRIPTION,
                    content = @Content(mediaType = AdaptersConstants.JSON, schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest orderRequest, HttpServletRequest request) {
        Claims claims = JwtValidate.JwtValidation(request);
        Long userId = claims.get(AdaptersConstants.USER_ID_FROM_TOKEN, Long.class);

        Order order = orderRequestMapper.toModel(orderRequest);

        orderServicePort.createOrder(userId, order);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
