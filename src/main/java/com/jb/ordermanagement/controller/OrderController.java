package com.jb.ordermanagement.controller;

import com.jb.ordermanagement.entity.Orders;
import com.jb.ordermanagement.enums.OrderStatus;
import com.jb.ordermanagement.service.OrderService;
import org.springframework.web.bind.annotation.*;

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
    public Orders createOrder(@RequestBody Orders order){
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
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
