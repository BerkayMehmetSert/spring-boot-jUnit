package com.bms.springboottest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CategoryAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleCategoryAlreadyExistException(CategoryAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleProductAlreadyExistException(ProductAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
