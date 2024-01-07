package com.example.restapi.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoProductFoundException extends RuntimeException{
    public NoProductFoundException(String message)
    {
        super(message);
    }
}
