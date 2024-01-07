package com.example.restapi.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class CustomerAlreadyExist extends RuntimeException{
    public CustomerAlreadyExist(String message)
    {
        super(message);
    }
}
