package com.Microservices.Department.Dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class DepartmentDto {
    private Long deptId;
    private String name;
    private String deptCode;
    private String deptDesc;
}
