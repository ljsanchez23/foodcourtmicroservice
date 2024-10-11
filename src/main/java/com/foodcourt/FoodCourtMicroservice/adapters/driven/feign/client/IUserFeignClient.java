package com.foodcourt.FoodCourtMicroservice.adapters.driven.feign.client;

import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.FoodCourtMicroservice.configuration.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = AdaptersConstants.USER_CLIENT_NAME,
url = AdaptersConstants.USER_CLIENT_URL,
configuration = FeignConfiguration.class)
public interface IUserFeignClient {
    @GetMapping(value = AdaptersConstants.GET_ROLE_BY_ID_URL)
    String getRoleById(@PathVariable Long id);
}
