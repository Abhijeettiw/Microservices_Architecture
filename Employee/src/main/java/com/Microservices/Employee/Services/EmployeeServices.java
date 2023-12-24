package com.Microservices.Employee.Services;

import com.Microservices.Employee.Dto.ApiResponseDto;
import com.Microservices.Employee.Dto.EmployeeDto;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface EmployeeServices {
    public EmployeeDto newEmployee(EmployeeDto employeeDto);

    public EmployeeDto updateEmployee(EmployeeDto employeeDto, Long empId);

    public List<EmployeeDto> empByEmail(String emailId);

    public List<EmployeeDto> empByFirstName(String firstName);

    public List<EmployeeDto> empByLastName(String lastName);

    public List<EmployeeDto> empByName(String Name);

    public ApiResponseDto empById(Long empId);

    public List<EmployeeDto> empByEmpCode(String empCode);

    public Void deleteById(Long empId);

    public List<EmployeeDto> findAllEmp();
}
