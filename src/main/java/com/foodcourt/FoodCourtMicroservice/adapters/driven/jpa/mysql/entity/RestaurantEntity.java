package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity;

import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = AdaptersConstants.RESTAURANT_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer ein;
    private String address;
    private String phone;
    private String urlLogo;
    private Long userId;
    @OneToMany(mappedBy = AdaptersConstants.RESTAURANT_DISH_RELATION, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DishEntity> dishes;
}
