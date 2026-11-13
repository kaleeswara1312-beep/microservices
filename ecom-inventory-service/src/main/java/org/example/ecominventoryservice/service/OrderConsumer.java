package org.example.ecominventoryservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.example.ecominventoryservice.service.OrderEvent;

@Service
public class OrderConsumer {

    @KafkaListener(
            topics = "orders-topic",
            groupId = "inventory-service-group"
    )
    public void consume(OrderEvent event) {

        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Customer ID: " + event.getCustomerId());
        System.out.println("Product ID: " + event.getProductId());
        System.out.println("Quantity: " + event.getQuantity());
        System.out.println("Status: " + event.getStatus());
    }
}