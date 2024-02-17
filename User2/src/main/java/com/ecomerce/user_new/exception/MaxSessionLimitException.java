package com.ecomerce.user_new.exception;

public class MaxSessionLimitException extends RuntimeException {
    public MaxSessionLimitException(String message) {
        super(message);
    }
}
