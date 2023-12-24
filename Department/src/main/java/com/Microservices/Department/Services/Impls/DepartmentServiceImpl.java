package com.Microservices.Department.Services.Impls;

import com.Microservices.Department.Dtos.DepartmentDto;
import com.Microservices.Department.Entity.Departments;
import com.Microservices.Department.Repository.DepartmentRepo;
import com.Microservices.Department.Services.DepartmentServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentServices {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DepartmentDto departmentDto;
    @Autowired
    private Departments departments;

    public DepartmentServiceImpl() {
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        departments = mapper.map(departmentDto, Departments.class);
        Departments newDepartment = departmentRepo.save(departments);
        return mapper.map(newDepartment,DepartmentDto.class);
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long deptId) {
        departments = departmentRepo.getReferenceById(deptId);
        if (departments != null) {
            departments.setName(departmentDto.getName());
            departments.setDeptCode(departmentDto.getDeptCode());
            departments.setDeptDesc(departmentDto.getDeptDesc());
            Departments newDept = departmentRepo.save(departments);
            return mapper.map(newDept, DepartmentDto.class);
        } else
            return null;
    }

    @Override
    public Void deleteDepartment(Long deptId) {
        if (deptId != null && deptId > 0)
            departmentRepo.deleteById(deptId);
        return null;
    }

    @Override
    public List<DepartmentDto> allDepartments() {
        List<Departments> allDept = departmentRepo.findAll();
        return allDept.stream().map(dept -> mapper.map(dept, DepartmentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDto> deptByName(String name) {
        List<Departments> dept = departmentRepo.findByName(name);
        return dept.stream().map(d->mapper.map(d, DepartmentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDto> deptByDeptCode(String code) {
        List<Departments> dept = departmentRepo.findByDeptCode(code);
        return dept.stream().map(d->mapper.map(d, DepartmentDto.class)).collect(Collectors.toList());
    }

    @Override
    public DepartmentDto deptById(Long deptId) {
        departments = departmentRepo.getReferenceById(deptId);
        if (departments != null) {
            return mapper.map(departments, DepartmentDto.class);
        } else
            return null;
    }
}
