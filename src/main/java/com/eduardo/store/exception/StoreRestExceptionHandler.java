package com.eduardo.store.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.zip.DataFormatException;

@RestControllerAdvice
public class StoreRestExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String EXCEPTION_MSG = "An exception occurred. Error code {}";


    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(RuntimeException ex, WebRequest request) {
        String msg = "Invalid argument exception";
        return handleExceptionInternal(ex, msg, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {DataFormatException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(RuntimeException ex, WebRequest request) {
        String msg = "Invalid data passed exception";
        return handleExceptionInternal(ex, msg, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
