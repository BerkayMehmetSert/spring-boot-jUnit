package com.bms.springboottest.exception;

public class ProductAlreadyExistException extends RuntimeException {
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}
