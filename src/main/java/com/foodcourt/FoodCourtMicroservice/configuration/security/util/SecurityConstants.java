package com.foodcourt.FoodCourtMicroservice.configuration.security.util;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class SecurityConstants {

    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_SECRET_KEY = "VOhduvxl4WR2azOsHG7ULDF2w+gRl4DDyCoqy9MsUWROOGoK0sPCuuM7sg2JPLFt";
    public static final String JWT_AUTHORITY = "authority";
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String APPLICATION_JSON = "application/json";
    public static final String WRITER = "{\"error\": \"Unauthorized\", \"message\": \"Invalid or missing token.\"}";
    public static final String UNCHECKED = "unchecked";
    public static final String INVALID_JWT_SIGNATURE = "Invalid JWT Signature";
    public static final String AUTHENTICATION_FAILED = "Authentication failed";
    public static final String OWNER_ROLE = "OWNER";
    public static final String CREATE_RESTAURANT_URL = "/restaurant";
    public static final String CREATE_DISH_URL = "/dish";
    public static final String UPDATE_DISH_URL = "/{dishId}";


    public static SecretKey getSignedKey(String secretKey){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
