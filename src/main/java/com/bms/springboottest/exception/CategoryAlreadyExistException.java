package com.bms.springboottest.exception;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
