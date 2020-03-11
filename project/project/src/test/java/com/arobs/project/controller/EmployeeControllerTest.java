package com.arobs.project.controller;

import com.arobs.project.entity.Employee;
import com.arobs.project.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    static List<Employee> employeeList = new ArrayList<>(5);


    @BeforeAll
    static void setUp() {
        employeeList.add(new Employee(1, "name1", "pass", "email1", "user"));
        employeeList.add(new Employee(2, "name2", "pass", "email2", "user"));
    }

    @Test
    void whenFindAll_given_returnResponseEntity() {
        when(employeeService.getAllEmployees()).thenReturn(employeeList);
        ResponseEntity responseEntity = employeeController.findAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


}
