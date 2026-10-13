package org.example.ecomorderservice.config.encodeDecode;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class FeignCustomEncoder implements Encoder {

    @Override
    public void encode(
            Object object,
            Type bodyType,
            RequestTemplate template) throws EncodeException {
        System.out.println("Encoder invoked");

        template.body(
                object.toString().getBytes(),
                StandardCharsets.UTF_8
        );
    }
}
