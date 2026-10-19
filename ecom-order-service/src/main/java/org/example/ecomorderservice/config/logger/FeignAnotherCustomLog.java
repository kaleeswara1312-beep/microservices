package org.example.ecomorderservice.config.logger;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.example.ecomorderservice.config.encodeDecode.FeignCustomDecoder;
import org.example.ecomorderservice.config.encodeDecode.FeignCustomEncoder;
import org.example.ecomorderservice.exception.FeignError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignAnotherCustomLog {

    @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }

//    @Bean
//    public Request.Options FeignOptions(){
//        return new Request.Options(2000, 5000);
//    }

    @Bean
    public Retryer retryer(){
        return new Retryer.Default(2000, 5000, 3);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(
                    "Authorization", "Bearer my-token"
            );
            requestTemplate.header(
                    "X-Client-Name", "order-service"
            );
        };
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new FeignError();
    }

    @Bean
    public Encoder encoder(){
        return new FeignCustomEncoder();
    }

    @Bean
    public Decoder decoder(){
        return new FeignCustomDecoder();
    }
}
