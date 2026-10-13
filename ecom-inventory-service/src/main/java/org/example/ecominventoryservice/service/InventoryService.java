package org.example.ecominventoryservice.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public String placeOrder(String productId) throws InterruptedException {
//        Thread.sleep(7000);
         if(productId.equals("1")){
             return "Order placed Successfully";
         }

         return "Product out of stock";
    }
}
