package com.Microservices.Department.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class DeptMessageController {
    @Value("${spring.boot.message}")
    private String message;

    @GetMapping("/dept/message")
    public String message(){
        return message;
    }

}
