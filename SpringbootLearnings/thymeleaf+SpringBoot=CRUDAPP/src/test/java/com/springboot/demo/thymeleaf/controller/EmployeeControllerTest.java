package com.springboot.demo.thymeleaf.controller;

import com.springboot.demo.thymeleaf.controller.EmployeeController;
import com.springboot.demo.thymeleaf.entity.Employee;
import com.springboot.demo.thymeleaf.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testListEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Adarsh", "Pandey", "adi@example.com"));

        when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/employees/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/list-employees"))
                .andExpect(model().attribute("employees", employees));
    }

    @Test
    public void testShowFormForAdd() throws Exception {
        mockMvc.perform(get("/employees/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/employee-form"))
                .andExpect(model().attributeExists("employee"));
    }

    @Test
    public void testShowFormForUpdate() throws Exception {
        Employee employee = new Employee(1, "Adarsh", "Pandey", "adi@example.com");

        when(employeeService.findById(anyInt())).thenReturn(employee);

        mockMvc.perform(post("/employees/showFormForUpdate")
                        .param("employeeId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/employee-form"))
                .andExpect(model().attribute("employee", employee));

        verify(employeeService, times(1)).findById(1);
    }

    @Test
    public void testSaveEmployee() throws Exception {
        mockMvc.perform(post("/employees/save")
                        .param("id", "1")
                        .param("firstName", "Adarsh")
                        .param("lastName", "Pandey")
                        .param("email", "adi@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/employees/list"));

        verify(employeeService, times(1)).save(any(Employee.class));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(post("/employees/delete")
                        .param("employeeId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/employees/list"));

        verify(employeeService, times(1)).deleteById(1);
    }

    @Test
    public void testListEmployeesEmptyList() throws Exception {
        when(employeeService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/employees/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees/list-employees"))
                .andExpect(model().attribute("employees", new ArrayList<>()));
    }

}
