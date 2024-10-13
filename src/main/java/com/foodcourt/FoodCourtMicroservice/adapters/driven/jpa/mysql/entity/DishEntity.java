package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity;

import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = AdaptersConstants.DISH_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String urlLogo;
    private Boolean status;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = AdaptersConstants.DISH_CATEGORY_JOIN_COLUMN, nullable = false)
    private CategoryEntity category;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = AdaptersConstants.DISH_RESTAURANT_JOIN_COLUMN, nullable = false)
    private RestaurantEntity restaurant;
}
