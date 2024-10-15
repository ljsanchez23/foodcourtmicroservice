package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findByEin(Integer ein);
    Optional<RestaurantEntity> findByName(String name);
    Optional<RestaurantEntity> findByUserId(Long userId);
    Optional<RestaurantEntity> findByEmployeesId(Long userId);
    @Modifying
    @Transactional
    @Query(value = AdaptersConstants.SAVE_EMPLOYEE_SQL_QUERY, nativeQuery = true)
    void saveEmployee(@Param(AdaptersConstants.RESTAURANT_ID) Long restaurantId, @Param(AdaptersConstants.EMPLOYEE_ID) Long employeeId);
    @Query(AdaptersConstants.IS_EMPLOYEE_ASSIGNED_SQL_QUERY)
    Boolean isEmployeeAssigned(@Param(AdaptersConstants.RESTAURANT_ID) Long restaurantId, @Param(AdaptersConstants.EMPLOYEE_ID) Long employeeId);
}
