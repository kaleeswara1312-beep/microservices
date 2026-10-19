package org.example.ecomorderservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.example.ecomorderservice.service.OrderService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final org.example.ecomorderservice.service.OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @Retryable(
//            retryFor = RuntimeException.class,
//            maxAttempts = 3,
//            backoff = @Backoff(delay = 2000)
//    )

//    @RateLimiter(name = "orderService", fallbackMethod = "orderRateLimitFallback")
//    @CircuitBreaker(
//            name = "circuitOrderService",
//            fallbackMethod = "orderCircuitFallback"
//    )

//    @TimeLimiter(name = "timeLimitOrderService", fallbackMethod = "orderTimeLimiterFallback")
    @Bulkhead(name = "bulkHeadOrderService",
            type = Bulkhead.Type.THREADPOOL,
            fallbackMethod = "orderBulkHeadFallback"
    )
    @GetMapping("/{productId}")
//    public String placeOrder(@PathVariable String productId) {
//        return orderService.placeOrder(productId);
//    }
    public CompletableFuture<String> placeOrder(@PathVariable String productId) {
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(productId));
    }

    // Rate Limiter fallback
    public String orderRateLimitFallback(
            String productId,
            RequestNotPermitted ex) {

        System.out.println("RATE LIMIT EXCEEDED!");
        return "Request limited. Please try again later.";
    }

    // Circuit Breaker fallback
    public String orderCircuitFallback(
            String productId,
            Throwable ex) {

        System.out.println("Circuit Breaker Invoked!");
        System.out.println("Reason: " + ex.getMessage());

        return "Inventory service is unavailable. Please try again later.";
    }

    // Time Limiter fallback
    public CompletableFuture<String> orderTimeLimiterFallback(
            String productId,
            Throwable ex) {

        System.out.println("Time Limiter invoked!");

        return CompletableFuture.supplyAsync(() -> "Time exceed for some reason");
    }

    // Bulkhead fallback
//    public String orderBulkHeadFallback(
//            String productId,
//            Throwable ex) {
//
//        System.out.println("Bulkhead invoked!");
//        System.out.println("Reason: " + ex.getMessage());
//
//        return "Bulkhead API rejected.";
//    }
    public CompletableFuture<String> orderBulkHeadFallback(
            String productId,
            Throwable ex) {

        System.out.println("ThreadPool Bulkhead invoked!");
        System.out.println("Reason: " + ex.getMessage());

        return CompletableFuture.completedFuture(
                "Bulkhead API rejected."
        );
    }
}
