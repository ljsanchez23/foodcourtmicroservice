package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IRestaurantEntityMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.foodcourt.FoodCourtMicroservice.domain.model.Restaurant;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IRestaurantPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
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
    public void saveEmployee(Long restaurantId, Long employeeId) {
        restaurantRepository.saveEmployee(restaurantId, employeeId);
    }

    @Override
    public Boolean existsById(Long id) {
        return restaurantRepository.existsById(id);
    }

    @Override
    public Boolean isEmployeeAssigned(Long restaurantId, Long employeeId) {
        return restaurantRepository.isEmployeeAssigned(restaurantId, employeeId);
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

    @Override
    public Optional<Restaurant> findById(Long id){
        return restaurantRepository.findById(id)
                .map(restaurantEntityMapper::toModel);
    }

    @Override
    public Optional<Restaurant> findByUserId(Long userId){
        return restaurantRepository.findByUserId(userId)
                .map(restaurantEntityMapper::toModel);
    }

    @Override
    public PagedResult<Restaurant> getAllRestaurants(Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<RestaurantEntity> restaurantPage = restaurantRepository.findAll(pageRequest);
        List<Restaurant> restaurants = restaurantPage.getContent()
                .stream()
                .map(restaurantEntityMapper::toModel)
                .toList();
        return new PagedResult<>(restaurants, restaurantPage.getNumber(), restaurantPage.getSize(), restaurantPage.getTotalElements());
    }

    @Override
    public Optional<Restaurant> findRestaurantByEmployeeId(Long userId) {
        return restaurantRepository.findByEmployeesId(userId)
                .map(restaurantEntityMapper::toModel);
    }
}
