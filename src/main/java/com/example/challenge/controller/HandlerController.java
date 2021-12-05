package com.example.challenge.controller;

import com.example.challenge.exception.NotContentException;
import com.example.challenge.util.Payload;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class HandlerController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NotContentException.class)
    public ResponseEntity<?>NotContentException(NotContentException notContentException){
        Payload payload = new Payload(notContentException.getMessage(),HttpStatus.BAD_REQUEST,new Date());
        return ResponseEntity.badRequest().body(payload);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Payload payload = new Payload("Por favor, cambie el método http",HttpStatus.BAD_REQUEST,new Date());
        return ResponseEntity.badRequest().body(payload);
    }
}
