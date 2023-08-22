package com.springboot.demo.thymeleaf.dao;


import com.springboot.demo.thymeleaf.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
