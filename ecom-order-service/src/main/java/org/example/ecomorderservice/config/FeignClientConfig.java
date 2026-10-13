package org.example.ecomorderservice.config;

import org.example.ecomorderservice.config.logger.FeignAnotherCustomLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Inventory" ,
        url = "http://localhost:8001",
        configuration = FeignAnotherCustomLog.class
)
public interface FeignClientConfig {

    @GetMapping("/inventory/{productId}")
    String placeOrder(@PathVariable String productId);
}
