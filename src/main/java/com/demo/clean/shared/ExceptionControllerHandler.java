package com.demo.clean.shared;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {DomainException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "{\"message:\" \"%s\", \"success\": false}";
        return this.handleExceptionInternal(ex, String.format(bodyOfResponse, ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
