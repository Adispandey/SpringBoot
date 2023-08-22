package com.springboot.demo.thymeleaf.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    public void testGettersAndSetters() {
        // Create an Employee object
        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com");

        // Test getter methods
        assertEquals(1, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmail());

        // Test setter methods
        employee.setId(2);
        employee.setFirstName("Jane");
        employee.setLastName("Smith");
        employee.setEmail("jane.smith@example.com");

        assertEquals(2, employee.getId());
        assertEquals("Jane", employee.getFirstName());
        assertEquals("Smith", employee.getLastName());
        assertEquals("jane.smith@example.com", employee.getEmail());
    }

    @Test
    public void testAllArgsConstructor() {
        // Create an Employee object using the AllArgsConstructor
        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com");

        assertEquals(1, employee.getId());
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("john.doe@example.com", employee.getEmail());
    }

    @Test
    public void testNoArgsConstructor() {
        // Create an Employee object using the NoArgsConstructor
        Employee employee = new Employee();

        // Test default values
        assertEquals(0, employee.getId());
        assertEquals(null, employee.getFirstName());
        assertEquals(null, employee.getLastName());
        assertEquals(null, employee.getEmail());
    }

    @Test
    public void testToString() {
        // Create an Employee object
        Employee employee = new Employee(1, "John", "Doe", "john.doe@example.com");

        // Test toString() method
        String expectedString = "Employee(id=1, firstName=John, lastName=Doe, email=john.doe@example.com)";
        assertEquals(expectedString, employee.toString());
    }
}

