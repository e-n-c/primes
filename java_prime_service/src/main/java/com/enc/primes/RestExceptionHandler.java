package com.enc.primes;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    protected ResponseEntity<Object> handleBadRequestExceptions(IllegalArgumentException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                String.format("%s %s", ex.getClass().getName(), ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> defaultErrorHandler(Exception ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                String.format("%s %s", ex.getClass().getName(), ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }
}
