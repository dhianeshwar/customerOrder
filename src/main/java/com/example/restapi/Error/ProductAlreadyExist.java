package com.example.restapi.Error;

public class ProductAlreadyExist extends RuntimeException{
    public ProductAlreadyExist(String message)
    {
        super(message);
    }
}
