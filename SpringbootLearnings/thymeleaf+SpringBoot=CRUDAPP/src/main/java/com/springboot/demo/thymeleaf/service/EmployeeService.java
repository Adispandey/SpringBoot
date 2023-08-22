package com.springboot.demo.thymeleaf.service;


import com.springboot.demo.thymeleaf.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    Employee findById(int theId);

    public void save(Employee theEmployee);

    public void deleteById(int  theId);

}
