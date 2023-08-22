package com.springboot.demo.thymeleaf.service;

import com.springboot.demo.thymeleaf.controller.EmployeeController;
import com.springboot.demo.thymeleaf.dao.EmployeeRepository;
import com.springboot.demo.thymeleaf.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(employeeRepository.existsById(1)).thenReturn(true);
    }

    @Test
    public void testFindAll() {
        // Prepare the mock data
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John" ,"Doe", "john@example.com"));
        employees.add(new Employee(2, "Jane", "Smith", "jane@example.com"));

        // Define the behavior of the mocked repository method
        when(employeeRepository.findAll()).thenReturn(employees);

        // Call the service method
        List<Employee> result = employeeService.findAll();

        // Check if the result matches the mock data
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe",result.get(0).getLastName());
        assertEquals("jane@example.com", result.get(1).getEmail());

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_ValidId() {
        // Prepare the mock data
        Employee employee = new Employee(1, "John", "Doe", "john@example.com");

        // Define the behavior of the mocked repository method
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        // Call the service method
        Employee result = employeeService.findById(1);

        // Check if the result matches the mock data
        assertEquals("John", result.getFirstName());
        assertEquals("Doe",result.getLastName());
        assertEquals("john@example.com", result.getEmail());

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).findById(1);
    }

    @Test
    public void testFindById_InvalidId() {
        // Define the behavior of the mocked repository method
        when(employeeRepository.findById(100)).thenReturn(Optional.empty());

        // Call the service method and expect a RuntimeException
        assertThrows(RuntimeException.class, () -> employeeService.findById(100));

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).findById(100);
    }

    @Test
    public void testSave() {
        // Prepare the mock data
        Employee employee = new Employee(1,"John" ,"Doe", "john@example.com");

        // Call the service method
        employeeService.save(employee);

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testDeleteById() {
        // Call the service method
        employeeService.deleteById(1);

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    public void testFindAll_EmptyList() {
        // Test case to check if findAll() handles an empty list of employees

        // Prepare an empty list
        List<Employee> emptyList = new ArrayList<>();

        // Define the behavior of the mocked repository method
        when(employeeRepository.findAll()).thenReturn(emptyList);

        // Call the service method
        List<Employee> result = employeeService.findAll();

        // Check if the result is an empty list
        assertTrue(result.isEmpty());

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testSave_NullEmployee() {
        // Set up mock behavior to throw IllegalArgumentException
        doThrow(IllegalArgumentException.class).when(employeeRepository).save(null);

        // Call the service method with a null employee and expect an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> employeeService.save(null));

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).save(null);
    }

    @Test
    public void testDeleteById_NonExistentId() {
        // Test case to check if deleteById() handles a non-existent employee ID

        // Define the behavior of the mocked repository method
        when(employeeRepository.existsById(100)).thenReturn(false);

        // Call the service method with a non-existent ID and expect a RuntimeException
        assertThrows(RuntimeException.class, () -> employeeService.deleteById(100));

        // Verify that the repository method was called
        verify(employeeRepository, times(1)).existsById(100);
        verify(employeeRepository, never()).deleteById(100);
    }

}

