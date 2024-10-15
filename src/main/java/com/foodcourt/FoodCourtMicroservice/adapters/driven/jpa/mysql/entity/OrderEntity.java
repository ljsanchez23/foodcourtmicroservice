package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity;

import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = AdaptersConstants.ORDER_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = AdaptersConstants.ORDER_RESTAURANT_JOIN_COLUMN, nullable = false)
    private RestaurantEntity restaurant;
    @OneToMany(mappedBy = AdaptersConstants.ORDER_MAPPED_BY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDishEntity> orderDishes = new ArrayList<>();
    private String status;
    private Long customerId;
}
