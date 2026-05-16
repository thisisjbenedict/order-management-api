package com.jb.ordermanagement.service;

import com.jb.ordermanagement.dto.CreateOrderRequest;
import com.jb.ordermanagement.entity.Orders;
import com.jb.ordermanagement.enums.OrderStatus;
import com.jb.ordermanagement.repository.OrderRepository;
import org.hibernate.query.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<Orders> getOrders(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("customerName").ascending());

        return orderRepository.findAll(pageable);
    }

    public Orders createOrder(CreateOrderRequest request) {

        Orders order = Orders.builder()
                .customerName(request.getCustomerName())
                .amount(request.getAmount())
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        return orderRepository.save(order);
    }

    public Orders updateStatus(UUID id, OrderStatus status) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return orderRepository.save(order);
    }

    public void delete(UUID id) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderRepository.delete(order);
    }
}