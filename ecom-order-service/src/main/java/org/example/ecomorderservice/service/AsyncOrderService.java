package org.example.ecomorderservice.service;

import java.time.Instant;
import java.util.UUID;

import org.example.ecomorderservice.config.RabbitMQConfig;
import org.example.ecomorderservice.dto.OrderCreatedEvent;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class AsyncOrderService {

    private final RabbitTemplate rabbitTemplate;

    public AsyncOrderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public String placeOrder(String productId) {

        OrderCreatedEvent event = new OrderCreatedEvent(
                UUID.randomUUID().toString(),
                productId,
                1,
                "CREATED",
                Instant.now()
        );

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                event
        );

        return "Order placed and event published successfully";
    }
}