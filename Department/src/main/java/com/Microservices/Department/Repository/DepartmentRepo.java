package com.Microservices.Department.Repository;

import com.Microservices.Department.Entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Departments,Long> {

    @Query(value = "Select d from Departments d where d.name like %:name%")
    public List<Departments> findByName(@Param("name") String name);

    @Query("select d from Departments d where d.deptCode like %:code%")
    public List<Departments> findByDeptCode(@Param("code") String code);
}
