package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IDishEntityMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IPagedResultMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IDishRepository;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IDishPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class DishAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IPagedResultMapper pagedResultMapper;

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
    @Override
    public PagedResult<Dish> findDishesByRestaurant(Long restaurantId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DishEntity> dishPage = dishRepository.findByRestaurantId(restaurantId, pageable);
        return pagedResultMapper.toModel(dishPage);
    }

    @Override
    public PagedResult<Dish> findDishesByRestaurantAndCategory(Long restaurantId, Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DishEntity> dishPage = dishRepository.findByRestaurantIdAndCategoryId(restaurantId, categoryId, pageable);
        return pagedResultMapper.toModel(dishPage);
    }
}
