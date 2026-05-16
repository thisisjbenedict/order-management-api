package com.jb.ordermanagement.controller;

import com.jb.ordermanagement.dto.CreateOrderRequest;
import com.jb.ordermanagement.entity.Orders;
import com.jb.ordermanagement.enums.OrderStatus;
import com.jb.ordermanagement.service.OrderService;

import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Orders createOrder(@RequestBody @Valid CreateOrderRequest request){
        return orderService.createOrder(request);
    }

    @GetMapping
    public Page<Orders> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return orderService.getOrders(page, size);
    }

    @PutMapping("/{id}/status")
    public Orders updateStatus(
            @PathVariable UUID id,
            @RequestParam OrderStatus status) {
        return  orderService.updateStatus(id,status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        orderService.delete(id);
    }
}
