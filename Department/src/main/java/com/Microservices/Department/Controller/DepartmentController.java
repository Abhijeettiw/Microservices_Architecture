package com.Microservices.Department.Controller;

import com.Microservices.Department.Dtos.DepartmentDto;
import com.Microservices.Department.Services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments/")
public class DepartmentController {

    @Autowired
    private DepartmentServices departmentServices;

    @PostMapping("newDept")
    public ResponseEntity<?> newDept(@RequestBody DepartmentDto departments) {
        return new ResponseEntity<>(departmentServices.createDepartment(departments), HttpStatus.CREATED);
    }

    @GetMapping("deptByName")
    public ResponseEntity<?> deptByName(@RequestParam String name) {
        List<DepartmentDto> depts = departmentServices.deptByName(name);
        return new ResponseEntity<>(depts, HttpStatus.FOUND);
    }
    @GetMapping("deptByDeptCode")
    public ResponseEntity<?> deptByDeptCode(@RequestParam("deptCode") String code) {
        List<DepartmentDto> depts = departmentServices.deptByDeptCode(code);
        return new ResponseEntity<>(depts, HttpStatus.FOUND);
    }

    @GetMapping("deptById/{deptId}")
    public ResponseEntity<?> deptById(@PathVariable(name = "deptId") Long id) {
        return new ResponseEntity<>(departmentServices.deptById(id), HttpStatus.FOUND);
    }

    @GetMapping("allDept")
    public ResponseEntity<?> allDept() {
        return new ResponseEntity<>(departmentServices.allDepartments(), HttpStatus.OK);
    }

    @PutMapping("updateDept")
    public ResponseEntity<?> updateDept(@RequestBody DepartmentDto departmentDto, @RequestParam Long id) {
        return new ResponseEntity<>(departmentServices.updateDepartment(departmentDto, id), HttpStatus.OK);
    }

    @DeleteMapping("deleteDept/{deptId}")
    public ResponseEntity<?> deleteDept(@PathVariable("deptId") Long id) {
        return new ResponseEntity<>(departmentServices.deleteDepartment(id), HttpStatus.OK);
    }
}
