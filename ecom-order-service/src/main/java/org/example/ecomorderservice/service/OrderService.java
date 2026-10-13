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

        return feignClientConfig.placeOrder(productId);

//        return restTemplateConfig.restTemplate().
//                getForObject("http://localhost:8001/inventory/" + productId, String.class);

        //        return restClient.get()
//                .uri("http://localhost:8001/inventory/{productId}" , productId)
//                .retrieve()
//                .body(String.class);
    }
}
