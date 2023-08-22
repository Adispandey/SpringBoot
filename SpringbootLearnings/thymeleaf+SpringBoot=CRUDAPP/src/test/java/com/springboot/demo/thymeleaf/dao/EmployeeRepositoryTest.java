package com.springboot.demo.thymeleaf.dao;

import com.springboot.demo.thymeleaf.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeRepository mockEmployeeRepository;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee(1,"John" ,"Doe", "john@example.com");

        when(mockEmployeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeRepository.save(employee);

        assertNotNull(savedEmployee);
        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("Doe", savedEmployee.getLastName());
        assertEquals("john@example.com", savedEmployee.getEmail());

        verify(mockEmployeeRepository, times(1)).save(employee);
    }

    @Test
    public void testFindEmployeeById() {
        Employee employee = new Employee(1,"Jane", "Smith", "jane@example.com");
        employee.setId(1);

        when(mockEmployeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Optional<Employee> retrievedEmployee = employeeRepository.findById(1);

        assertTrue(retrievedEmployee.isPresent());
        assertEquals("Jane", retrievedEmployee.get().getFirstName());
        assertEquals("Smith", retrievedEmployee.get().getLastName());
        assertEquals("jane@example.com", retrievedEmployee.get().getEmail());

        verify(mockEmployeeRepository, times(1)).findById(1);
    }

    @Test
    public void testFindAllEmployees() {
        List<Employee> employees = List.of(
                new Employee(1,"John" ,"Doe", "john@example.com"),
                new Employee(2,"Jane", "Smith", "jane@example.com")
        );

        when(mockEmployeeRepository.findAll()).thenReturn(employees);

        List<Employee> retrievedEmployees = employeeRepository.findAll();

        assertEquals(2, retrievedEmployees.size());
        assertEquals("John", retrievedEmployees.get(0).getFirstName());
        assertEquals("Doe", retrievedEmployees.get(0).getLastName());
        assertEquals("jane@example.com", retrievedEmployees.get(1).getEmail());

        verify(mockEmployeeRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateEmployee() {
        Employee employee = new Employee(1,"Bob" ,"Johnson", "bob@example.com");
        employee.setId(1);

        when(mockEmployeeRepository.save(employee)).thenReturn(employee);
        when(mockEmployeeRepository.existsById(1)).thenReturn(true);

        Employee updatedEmployee = employeeRepository.save(employee);

        assertEquals("Bob", updatedEmployee.getFirstName());
        assertEquals("Johnson", updatedEmployee.getLastName());
        assertEquals("bob@example.com", updatedEmployee.getEmail());

        verify(mockEmployeeRepository, times(1)).save(employee);
    }

    @Test
    public void testDeleteEmployee() {
        int employeeIdToDelete = 1;

        when(mockEmployeeRepository.existsById(employeeIdToDelete)).thenReturn(true);

        employeeRepository.deleteById(employeeIdToDelete);

        verify(mockEmployeeRepository, times(1)).deleteById(employeeIdToDelete);
    }
}
