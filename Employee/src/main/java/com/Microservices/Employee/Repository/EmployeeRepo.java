package com.Microservices.Employee.Repository;

import com.Microservices.Employee.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    @Query("select e from Employee e where e.firstName like %:firstName%")
    public List<Employee> findByFirstName(String firstName);
    @Query("select e from Employee e where e.lastName like %:lastName%")
    public List<Employee> findByLastName(String lastName);
    @Query("select e from Employee e where e.fullName like %:fullName%")
    public List<Employee> findByFullName(String fullName);
    @Query("select e from Employee e where e.emailId like %:emailId%")
    public List<Employee> findByEmailId(String emailId);
    @Query("select e from Employee e where e.empCode like %:empCode%")
    public List<Employee> findByEmpCode(String empCode);
}
