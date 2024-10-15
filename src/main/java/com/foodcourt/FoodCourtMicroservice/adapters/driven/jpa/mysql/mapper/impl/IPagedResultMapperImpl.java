package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IPagedResultMapper;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IPagedResultMapperImpl implements IPagedResultMapper {

    @Override
    public PagedResult<Dish> toModel(Page<DishEntity> dishPage) {
        List<Dish> dishes = dishPage.getContent().stream()
                .map(this::mapToDomain)
                .toList();

        return new PagedResult<>(
                dishes,
                dishPage.getNumber(),
                dishPage.getSize(),
                dishPage.getTotalElements()
        );
    }

    private Dish mapToDomain(DishEntity dishEntity) {
        return new Dish(
                dishEntity.getId(),
                dishEntity.getName(),
                dishEntity.getPrice(),
                dishEntity.getDescription(),
                dishEntity.getUrlLogo(),
                dishEntity.getStatus(),
                dishEntity.getCategory().getId(),
                dishEntity.getRestaurant().getId()
        );
    }
}
