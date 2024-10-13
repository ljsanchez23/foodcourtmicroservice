package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IDishEntityMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IDishRepository;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IDishPersistencePort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class DishAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void saveDish(Dish dish){
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public Optional<Dish> findDishById(Long id) {
        return dishRepository.findDishById(id)
                .map(dishEntityMapper::toModel);
    }

    @Override
    public boolean existsByName(String name){
        return dishRepository.findByName(name).isPresent();
    }
}
