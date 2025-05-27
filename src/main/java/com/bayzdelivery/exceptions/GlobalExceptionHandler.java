package com.bayzdelivery.exceptions;

import java.util.AbstractMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        LOG.error("No handler found for request: ", exception);
        AbstractMap.SimpleEntry<String, String> response =
                new AbstractMap.SimpleEntry<>("message", "Resource not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handleGeneralException(Exception exception) {
        LOG.error("Request could not be processed: ", exception);
        AbstractMap.SimpleEntry<String, String> response =
                new AbstractMap.SimpleEntry<>("message", "Request could not be processed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
