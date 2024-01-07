package com.example.restapi.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainError {
    @ExceptionHandler(NoProductFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoProductException(NoProductFoundException e)
    {
        ErrorResponse errorResponse=new ErrorResponse(404,e.getMessage(),"Enter a valid Product id and try again");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleProductAlreadyExists(ProductAlreadyExist e)
    {
        ErrorResponse errorResponse=new ErrorResponse(201,e.getMessage(),"Try with the new ID");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException e)
    {
        ErrorResponse errorResponse=new ErrorResponse(404,e.getMessage(),e.getSolution());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleCustomerAlreadyExists(CustomerAlreadyExist e)
    {
        ErrorResponse errorResponse=new ErrorResponse(201,e.getMessage(),"Try with the new ID");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotAvailable.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotAvailable e)
    {
        ErrorResponse errorResponse=new ErrorResponse(201,e.getMessage(),"Try for another available products");
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoSuchOrder.class)
    public ResponseEntity<ErrorResponse> noSuchOrder(NoSuchOrder e)
    {
        ErrorResponse errorResponse=new ErrorResponse(404,e.getMessage(),e.getSolution());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
