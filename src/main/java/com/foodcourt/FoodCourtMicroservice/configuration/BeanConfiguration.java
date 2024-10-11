package com.foodcourt.FoodCourtMicroservice.configuration;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.feign.adapter.UserAdapter;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.feign.client.IUserFeignClient;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter.RestaurantAdapter;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.foodcourt.FoodCourtMicroservice.domain.api.IRestaurantServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.api.impl.RestaurantUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IUserPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    private final IUserFeignClient userFeignClient;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    public BeanConfiguration(IUserFeignClient userFeignClient, IRestaurantRepository restaurantRepository, IRestaurantEntityMapper restaurantEntityMapper) {
        this.userFeignClient = userFeignClient;
        this.restaurantRepository = restaurantRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserAdapter(userFeignClient);
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort(), userPersistencePort());
    }
}
