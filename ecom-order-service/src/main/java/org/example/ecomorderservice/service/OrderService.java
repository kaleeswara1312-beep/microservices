package org.example.ecomorderservice.service;

import org.example.ecomorderservice.config.FeignClientConfig;
import org.example.ecomorderservice.config.RestTemplateConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderService {

//    private final RestTemplateConfig restTemplateConfig;
    private final RestClient restClient;
    private final FeignClientConfig feignClientConfig;

    public OrderService(RestClient restClient, FeignClientConfig feignClientConfig) {
//        this.restTemplateConfig = restTemplateConfig;
        this.restClient = restClient;
        this.feignClientConfig = feignClientConfig;
    }

    public String placeOrder(String productId){

        // Call the private method
        String validatedProductId = validateProductId(productId);
        return feignClientConfig.placeOrder(validatedProductId);

//        return restTemplateConfig.restTemplate().
//                getForObject("http://localhost:8001/inventory/" + productId, String.class);

        //        return restClient.get()
//                .uri("http://localhost:8001/inventory/{productId}" , productId)
//                .retrieve()
//                .body(String.class);
    }

    private String validateProductId(String productId) {

        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException(
                    "Product ID cannot be null or blank");
        }

        return productId.trim();
    }

    public void cancelOrder(String productId){
        System.out.println("Do nothing: " + productId);
    }
}
