package org.example.ecominventoryservice.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public String placeOrder(String productId) throws Exception {
        Thread.sleep(15000);
         if(productId.equals("1")){
             return "Order placed Successfully";
         }

         return "Product out of stock";
//        System.out.println("Triggered inventory service");
//        throw new Exception("Service is not availalbe");
    }
}
