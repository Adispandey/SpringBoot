package com.springboot.demo.thymeleaf.service;

import com.springboot.demo.thymeleaf.dao.EmployeeRepository;
import com.springboot.demo.thymeleaf.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    //constructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){

        employeeRepository =theEmployeeRepository ;
    }

    @Override
    public List<Employee> findAll() {

        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {

        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;

    }

    @Override
    public void save(Employee theEmployee) {

            employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        if (employeeRepository.existsById(theId)) {
            employeeRepository.deleteById(theId);
        } else {
            throw new RuntimeException("Employee with ID " + theId + " does not exist");
        }
    }
}
