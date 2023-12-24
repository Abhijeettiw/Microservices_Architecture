package com.Microservices.Employee.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomExceptionsHandler extends RuntimeException {
    private String description;
    private String message;
}
