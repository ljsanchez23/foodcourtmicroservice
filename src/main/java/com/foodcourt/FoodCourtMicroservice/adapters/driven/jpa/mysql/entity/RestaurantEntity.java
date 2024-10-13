package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity;

import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import com.foodcourt.FoodCourtMicroservice.domain.util.Constants;
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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer ein;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String urlLogo;
    @Column(nullable = false)
    private Long userId;
    @OneToMany(mappedBy = AdaptersConstants.RESTAURANT_DISH_RELATION, cascade = CascadeType.ALL)
    private List<DishEntity> dishes;
}
