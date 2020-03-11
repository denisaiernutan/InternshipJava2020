package com.arobs.project.controller;

import com.arobs.project.converter.EmployeeConverter;
import com.arobs.project.dto.employee.EmployeeNewPassDTO;
import com.arobs.project.dto.employee.EmployeeWithPassDTO;
import com.arobs.project.entity.Employee;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        if (employeeList.isEmpty()) {
            return new ResponseEntity<>(" smth went wrong", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(employeeList
                    .stream()
                    .map(EmployeeConverter::convertToEmployeeDTO)
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> insertEmployee(@RequestBody @Valid EmployeeWithPassDTO employeeDTO) {
        try {
            return new ResponseEntity<>(EmployeeConverter.convertToEmployeeWithPassDTO(employeeService.insertEmployee(EmployeeConverter.convertToEntity(employeeDTO))), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePasswordEmployee(@RequestBody @Valid EmployeeNewPassDTO employeeNewPassDTO) {
        try {
            return new ResponseEntity<>(employeeService.updatePasswordEmployee(employeeNewPassDTO), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") int employeeId) {
        try {
            return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
