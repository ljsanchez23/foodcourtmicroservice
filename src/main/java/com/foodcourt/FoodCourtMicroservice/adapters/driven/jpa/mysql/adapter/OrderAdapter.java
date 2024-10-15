package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IOrderEntityMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IOrderRepository;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IOrderPersistencePort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderAdapter implements IOrderPersistencePort {
    private IOrderRepository orderRepository;
    private IOrderEntityMapper orderEntityMapper;

    @Override
    public void saveOrder(Order order){
        orderRepository.save(orderEntityMapper.toEntity(order));
    }


    @Override
    public Boolean existsByCustomerIdAndStatusIn(Long customerId, List<String> statuses){
        return orderRepository.existsByCustomerIdAndStatusIn(customerId, statuses);
    }
}
