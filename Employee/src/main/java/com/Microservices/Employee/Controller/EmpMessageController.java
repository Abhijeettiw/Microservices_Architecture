package com.Microservices.Employee.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class EmpMessageController {
    @Value("${spring.boot.message}")
    private String message;

    @GetMapping("/emp/message")
    public String message(){
        return message;
    }

}
