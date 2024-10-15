package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity;
import com.foodcourt.FoodCourtMicroservice.adapters.util.AdaptersConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = AdaptersConstants.ORDER_DISH_TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = AdaptersConstants.ORDER_JOIN_COLUMN, nullable = false)
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = AdaptersConstants.DISH_JOIN_COLUMN, nullable = false)
    private DishEntity dish;
    @Column(nullable = false)
    private Integer quantity;
}
