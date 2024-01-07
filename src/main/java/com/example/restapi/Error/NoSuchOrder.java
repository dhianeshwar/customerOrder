package com.example.restapi.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class NoSuchOrder extends RuntimeException{
    private final String solution;
    public NoSuchOrder(String message, String solution)
    {
        super(message);
        this.solution=solution;
    }

    public String getSolution() {
        return solution;
    }
}