package com.foodcourt.FoodCourtMicroservice.domain.api.impl;

import com.foodcourt.FoodCourtMicroservice.domain.api.IDishServicePort;
import com.foodcourt.FoodCourtMicroservice.domain.exception.DishAlreadyExistsException;
import com.foodcourt.FoodCourtMicroservice.domain.exception.DishDoesNotExistsException;
import com.foodcourt.FoodCourtMicroservice.domain.model.Dish;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IDishPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
import com.foodcourt.FoodCourtMicroservice.domain.util.UpdateDish;
import com.foodcourt.FoodCourtMicroservice.domain.util.Validator;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void saveDish(Dish dish) {
        if (dish.getStatus() == null) {
            dish.setStatus(true);
        }

        Validator.validateDish(dish);
        if (dishPersistencePort.existsByName(dish.getName())) {
            throw new DishAlreadyExistsException(Constants.DISH_ALREADY_EXISTS);
        }

        dishPersistencePort.saveDish(dish);
    }

    @Override
    public void updateDish(Long id, UpdateDish updateDish) {
        Dish dish = dishPersistencePort.findDishById(id)
                .orElseThrow(() -> new DishDoesNotExistsException(Constants.DISH_DOES_NOT_EXISTS));
        if (updateDish.getPrice() != null) {
            dish.setPrice(updateDish.getPrice());
        }

        if (updateDish.getDescription() != null) {
            dish.setDescription(updateDish.getDescription());
        }
        dishPersistencePort.saveDish(dish);
    }
}
