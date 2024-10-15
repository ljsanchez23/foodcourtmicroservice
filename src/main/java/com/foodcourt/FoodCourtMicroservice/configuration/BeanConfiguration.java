package com.foodcourt.FoodCourtMicroservice.configuration;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.feign.adapter.UserAdapter;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.feign.client.IUserFeignClient;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter.DishAdapter;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter.OrderAdapter;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter.RestaurantAdapter;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.*;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IDishRepository;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IOrderRepository;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.foodcourt.FoodCourtMicroservice.domain.api.ICategoryServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.api.IDishServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.api.IOrderServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.api.IRestaurantServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.api.impl.CategoryUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.api.impl.DishUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.api.impl.OrderUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.api.impl.RestaurantUseCase;
import com.foodcourt.FoodCourtMicroservice.domain.spi.*;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BeanConfiguration {
    private final IUserFeignClient userFeignClient;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IPagedResultMapper pagedResultMapper;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

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

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishAdapter(dishRepository, dishEntityMapper, pagedResultMapper);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort(), restaurantPersistencePort(), categoryPersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort(), restaurantPersistencePort());
    }
}
