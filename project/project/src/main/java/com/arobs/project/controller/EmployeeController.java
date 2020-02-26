package com.arobs.project.controller;

import com.arobs.project.dto.EmployeeDTO;
import com.arobs.project.dto.EmployeeNewPassDTO;
import com.arobs.project.dto.EmployeeWithPassDTO;
import com.arobs.project.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public ResponseEntity<?>insertEmployee(@RequestBody @Valid EmployeeWithPassDTO employeeDTO) {
        try {
            return new ResponseEntity<>(employeeService.insertEmployee(employeeDTO), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public EmployeeWithPassDTO updatePasswordEmployee(@RequestBody @Valid EmployeeNewPassDTO employeeNewPassDTO) {
        return employeeService.updatePasswordEmployee(employeeNewPassDTO);
    }

    @DeleteMapping(value = "/{email}")
    public boolean deleteEmployee(@PathVariable("email") String emailEmployee) {
        return employeeService.deleteEmployee(emailEmployee);
    }


}
