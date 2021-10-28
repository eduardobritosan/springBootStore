package com.eduardo.store.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class StoreRestExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String EXCEPTION_MSG = "An exception occurred. Error code {}";


    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(RuntimeException ex, WebRequest request) {
        String msg = "Invalid argument exception";
        return handleExceptionInternal(ex, msg, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
