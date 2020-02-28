package com.arobs.project.controller;

import com.arobs.project.dto.EmployeeDTO;
import com.arobs.project.dto.EmployeeNewPassDTO;
import com.arobs.project.dto.EmployeeWithPassDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.EmployeeService;
import com.arobs.project.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public List<EmployeeDTO> getAll() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<?> insertEmployee(@RequestBody @Valid EmployeeWithPassDTO employeeDTO) {
        try {
            return new ResponseEntity<>(employeeService.insertEmployee(employeeDTO), HttpStatus.OK);
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
