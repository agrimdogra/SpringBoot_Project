package com.ecomerce.dograshopping.exceptions;

public class InvalidProductExceptionError extends RuntimeException{
    public InvalidProductExceptionError(String message) {
        super(message);
    }
}
