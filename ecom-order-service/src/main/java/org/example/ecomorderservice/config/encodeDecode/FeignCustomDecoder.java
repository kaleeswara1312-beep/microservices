package org.example.ecomorderservice.config.encodeDecode;

import feign.Response;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public class FeignCustomDecoder implements Decoder {

    @Override
    public Object decode(
            Response response,
            Type type) throws IOException {

        System.out.println("Decoder invoked");

        if (response.body() == null) {
            return null;
        }

        try (var inputStream = response.body().asInputStream()) {
            return new String(
                    inputStream.readAllBytes(),
                    StandardCharsets.UTF_8
            );
        }
    }
}
