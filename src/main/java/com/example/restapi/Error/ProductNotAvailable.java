package com.example.restapi.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotAvailable extends RuntimeException {
    public ProductNotAvailable(String message,String name)
    {
        super(message+"(product:"+name+")");
    }
}
