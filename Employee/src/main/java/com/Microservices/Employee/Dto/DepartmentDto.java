package com.Microservices.Employee.Dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DepartmentDto {
    private Long deptId;
    private String name;
    private String deptCode;
    private String deptDesc;
}
