package com.Microservices.Employee.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Table(name = "employees")
@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Component
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailId;
    private String contactNo;
    private String empCode;
    private Long deptId;
    private Long orgId;
}
