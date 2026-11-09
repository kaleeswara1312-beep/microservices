package org.example.ecomorderservice.dto;

import java.time.Instant;

public class OrderCreatedEvent {

    private String eventId;
    private String productId;
    private int quantity;
    private String status;
    private Instant createdAt;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String eventId, String productId,
                             int quantity, String status, Instant createdAt) {
        this.eventId = eventId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}