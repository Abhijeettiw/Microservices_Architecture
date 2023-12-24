package com.Microservices.Employee.Services;

import com.Microservices.Employee.Dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080/departments/",value = "Department-Service")
@FeignClient(value = "DEPARTMENT-SERVICES",path = "/departments/") // name on which microservices is registered as client on eureka
public interface FeignClientApi {
    @GetMapping("deptById/{deptId}")
    public DepartmentDto deptById(@PathVariable(name = "deptId") Long id);
}
