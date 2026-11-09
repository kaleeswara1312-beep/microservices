package org.example.ecominventoryservice.service;

import org.example.ecominventoryservice.dto.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @RabbitListener(queues = "inventory.order.queue")
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) throws InterruptedException {

        Thread.sleep(5000);
        System.out.println("Order event received!");

        System.out.println("Event ID: " + event.getEventId());
        System.out.println("Product ID: " + event.getProductId());
        System.out.println("Quantity: " + event.getQuantity());
        System.out.println("Status: " + event.getStatus());
        System.out.println("Created At: " + event.getCreatedAt());

        // Inventory processing logic will come here
    }
}