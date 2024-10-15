package com.foodcourt.FoodCourtMicroservice.adapters.driving.controller;

import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.AssignOrderRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.dto.request.OrderRequest;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IAssignOrderRequestMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driving.mapper.IOrderRequestMapper;
import com.foodcourt.FoodCourtMicroservice.configuration.security.jwt.JwtValidate;
import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.FoodCourtMicroservice.domain.api.IOrderServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AdaptersConstants.ORDER_CONTROLLER_URL)
@AllArgsConstructor
public class OrderController {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IAssignOrderRequestMapper assignOrderRequestMapper;

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

    @Operation(summary = AdaptersConstants.LIST_ORDERS_ENDPOINT_SUMMARY, description = AdaptersConstants.LIST_ORDERS_ENDPOINT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = AdaptersConstants.OK, description = AdaptersConstants.LIST_ORDERS_OK_DESCRIPTION),
            @ApiResponse(responseCode = AdaptersConstants.BAD_REQUEST, description = AdaptersConstants.LIST_ORDERS_BAD_REQUEST_DESCRIPTION),
            @ApiResponse(responseCode = AdaptersConstants.UNAUTHORIZED, description = AdaptersConstants.LIST_ORDER_UNAUTHORIZED_DESCRIPTION)
    })
    @GetMapping
    public ResponseEntity<PagedResult<Order>> listOrders(
            @RequestParam(value = AdaptersConstants.PAGE_PARAM_VALUE, required = false) Integer page,
            @RequestParam(value = AdaptersConstants.SIZE_PARAM_VALUE, required = false) Integer size,
            @RequestParam(value = AdaptersConstants.STATUS_PARAM_VALUE, required = false) String status,
            HttpServletRequest request) {
        Claims claims = JwtValidate.JwtValidation(request);
        Long userId = claims.get(AdaptersConstants.USER_ID_FROM_TOKEN, Long.class);

        PagedResult<Order> orders = orderServicePort.listOrders(userId, page, size, status);

        return ResponseEntity.ok(orders);
    }

    @PutMapping(AdaptersConstants.ASSIGN_ORDER_ENDPOINT_URL)
    public ResponseEntity<Void> assignOrder(@PathVariable Long orderId, @RequestBody AssignOrderRequest assignOrderRequest,
                                            HttpServletRequest request){
        Claims claims = JwtValidate.JwtValidation(request);
        Long userId = claims.get(AdaptersConstants.USER_ID_FROM_TOKEN, Long.class);
        String status = assignOrderRequestMapper.extractNewStatusFromRequest(assignOrderRequest);
        orderServicePort.assignOrder(orderId, userId, status);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
