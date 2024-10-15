package com.foodcourt.FoodCourtMicroservice.adapters.driven.feign.adapter;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.feign.client.IUserFeignClient;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IUserPersistencePort;

public class UserAdapter implements IUserPersistencePort {

    private final IUserFeignClient userFeignClient;

    public UserAdapter(IUserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public String getRole(Long id){
        return userFeignClient.getRoleById(id);
    }
}
