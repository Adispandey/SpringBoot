package com.springboot.demo.crudapp.dao;


import com.springboot.demo.crudapp.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    public List<Employee> findAll();

    Employee findById(int theId);

    public void save(Employee theEmployee);

    public void deleteById(int theId);

}
