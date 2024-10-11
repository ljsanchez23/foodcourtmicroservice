package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByEin(Integer ein);
    Optional<RestaurantEntity> findByName(String name);
}
