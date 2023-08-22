package com.springboot.demo.crudapp.dao;


import com.springboot.demo.crudapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
