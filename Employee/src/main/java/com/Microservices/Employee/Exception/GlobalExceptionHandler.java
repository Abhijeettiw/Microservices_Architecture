package com.Microservices.Employee.Exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllException(Exception exception, WebRequest request) {
        CustomExceptionsHandler exceptionsHandler = new CustomExceptionsHandler();
        exceptionsHandler.setMessage(exception.getLocalizedMessage());
        exceptionsHandler.setDescription(request.getDescription(true));
        return new ResponseEntity<>(exceptionsHandler, HttpStatus.BAD_REQUEST);
    }
}
