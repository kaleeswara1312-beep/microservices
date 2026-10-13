package org.example.ecomorderservice.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        System.out.println("Error logged here");
        if(response.status() == 404){
            System.out.println("404 Error logged here");

            return new RuntimeException("URL not found");
        }

        return new RuntimeException("General Exception: " + response.status());
    }
}
