package com.Microservices.Department.Services;

import com.Microservices.Department.Dtos.DepartmentDto;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface DepartmentServices {
    public DepartmentDto createDepartment(DepartmentDto departmentDto);

    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long deptId);

    public Void deleteDepartment(Long deptId);

    public List<DepartmentDto> allDepartments();

    public List<DepartmentDto> deptByName(String name);
    public List<DepartmentDto> deptByDeptCode(String code);

    public DepartmentDto deptById(Long deptId);
}
