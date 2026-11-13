
package org.example.ecomorderservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderEvent(OrderEvent event) {

        kafkaTemplate.send(
                "orders-topic",
                event.getOrderId(),
                event
        );

        System.out.println("Order event sent: " + event.getOrderId());
    }
}
