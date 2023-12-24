package com.Microservices.Employee.Controller;

import com.Microservices.Employee.Dto.EmployeeDto;
import com.Microservices.Employee.Services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees/")
public class EmployeeController {
    @Autowired
    private EmployeeServices employeeServices;

    @PostMapping("newEmployee")
    public ResponseEntity<?> createEmp(@RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeServices.newEmployee(employeeDto), HttpStatus.CREATED);
    }
    @PutMapping("updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto,@RequestParam("id") Long empId){
        return  new ResponseEntity<>(employeeServices.updateEmployee(employeeDto,empId),HttpStatus.OK);
    }
    @GetMapping("allEmp")
    public ResponseEntity<?> allEmp(){
        return new ResponseEntity<>(employeeServices.findAllEmp(),HttpStatus.FOUND);
    }
    @GetMapping("empById/{empId}")
    public ResponseEntity<?> empById(@PathVariable Long empId){
        return new ResponseEntity<>(employeeServices.empById(empId),HttpStatus.FOUND);
    }
    @GetMapping("empByEmail")
    public ResponseEntity<?> findByEmail(@RequestParam String emailId){
        return new ResponseEntity<>(employeeServices.empByEmail(emailId),HttpStatus.FOUND);
    }
    @GetMapping("empByFirstName")
    public ResponseEntity<?> findByFirstName(@RequestParam String firstName){
        return new ResponseEntity<>(employeeServices.empByFirstName(firstName),HttpStatus.FOUND);
    }
    @GetMapping("empByLastName")
    public ResponseEntity<?> findByLastName(@RequestParam String lastName){
        return new ResponseEntity<>(employeeServices.empByLastName(lastName),HttpStatus.FOUND);
    }
    @GetMapping("empByFullName")
    public ResponseEntity<?> findByFullName(@RequestParam String fullName){
        return new ResponseEntity<>(employeeServices.empByName(fullName),HttpStatus.FOUND);
    }
    @GetMapping("empByEmpCode")
    public ResponseEntity<?> findByEmpCode(@RequestParam String empCode){
        return new ResponseEntity<>(employeeServices.empByEmpCode(empCode),HttpStatus.FOUND);
    }
    @DeleteMapping("deleteEmp/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long empId)
    {
        return new ResponseEntity<>(employeeServices.deleteById(empId),HttpStatus.OK);
    }
}
