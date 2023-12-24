package com.Microservices.Department.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "departments")
@Getter
@Setter
@RequiredArgsConstructor
@Component
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;
    private String name;
    private String deptCode;
    private String deptDesc;
}
