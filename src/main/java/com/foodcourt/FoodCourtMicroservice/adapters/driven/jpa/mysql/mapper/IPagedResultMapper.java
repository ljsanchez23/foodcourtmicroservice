package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
import org.springframework.data.domain.Page;

public interface IPagedResultMapper {
    PagedResult<Dish> toModel(Page<DishEntity> dishPage);
}
