package com.Microservices.Employee.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EmployeeDto {
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
