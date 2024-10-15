package com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.mapper.IOrderEntityMapper;
import com.foodcourt.FoodCourtMicroservice.adapters.driven.jpa.mysql.repository.IOrderRepository;
import com.foodcourt.FoodCourtMicroservice.domain.model.Order;
import com.foodcourt.FoodCourtMicroservice.domain.spi.IOrderPersistencePort;
import com.foodcourt.FoodCourtMicroservice.domain.util.PagedResult;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

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

    @Override
    public PagedResult<Order> findOrdersByRestaurantIdAndStatus(Long id, String status, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderEntity> orderPage = orderRepository.findAll(pageRequest);
        List<Order> orders = orderPage.getContent()
                .stream()
                .map(orderEntityMapper::toModel)
                .toList();
        return new PagedResult<>(orders, orderPage.getNumber(), orderPage.getSize(), orderPage.getTotalElements());
    }

    @Override
    public void assignOrder(Long orderId, Long userId, String status) {
        orderRepository.findById(orderId).ifPresent(orderEntity -> {
            orderEntity.setEmployeeIdAssigned(userId);
            orderEntity.setStatus(status);
            orderRepository.save(orderEntity);
        });
    }

    @Override
    public Boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id)
                .map(orderEntityMapper::toModel);
    }
}
