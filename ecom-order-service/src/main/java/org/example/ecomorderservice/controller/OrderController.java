package org.example.ecomorderservice.controller;

import org.example.ecomorderservice.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final org.example.ecomorderservice.service.OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{productId}")
    public String placeOrder(@PathVariable String productId){
        return orderService.placeOrder(productId);
    }
}
