package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;

import java.util.Optional;

public class RestaurantAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    public RestaurantAdapter(IRestaurantRepository restaurantRepository, IRestaurantEntityMapper restaurantEntityMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public Optional<Restaurant> findRestaurantByEin(Integer ein) {
        return restaurantRepository.findByEin(ein)
                .map(restaurantEntityMapper::toModel);
    }

    @Override
    public Optional<Restaurant> findRestaurantByName(String name){
        return restaurantRepository.findByName(name)
                .map(restaurantEntityMapper::toModel);
    }
}
