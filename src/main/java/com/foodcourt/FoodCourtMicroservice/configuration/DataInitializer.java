package com.foodcourt.FoodCourtMicroservice.configuration;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IRestaurantRepository;
import com.foodcourt.FoodCourtMicroservice.configuration.util.DataFactory;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final ICategoryRepository categoryRepository;
    private final IRestaurantRepository restaurantRepository;

    @PostConstruct
    public void init() {
        if (!categoryRepository.existsById(DataFactory.DEFAULT_CATEGORY.getId())) {
            categoryRepository.save(DataFactory.DEFAULT_CATEGORY);
        }
        if (!restaurantRepository.existsById(DataFactory.ADMIN_RESTAURANT.getId())) {
            restaurantRepository.save(DataFactory.ADMIN_RESTAURANT);
        }
        if(!restaurantRepository.existsById(DataFactory.OWNER_RESTAURANT.getId())){
            restaurantRepository.save(DataFactory.OWNER_RESTAURANT);
        }
    }
}
