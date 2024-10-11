package com.foodcourt.FoodCourtMicroservice.domain.spi;

public interface IUserPersistencePort {
    String getRole(Long id);
}
