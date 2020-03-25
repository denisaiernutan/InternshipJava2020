package com.arobs.project.controller;

import com.arobs.project.converter.EmployeeConverter;
import com.arobs.project.dto.employee.EmployeeNewPassDTO;
import com.arobs.project.dto.employee.EmployeeWithPassDTO;
import com.arobs.project.entity.Employee;
import com.arobs.project.enums.EmployeeRole;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger("FILE");

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
            EmployeeRole.valueOf(employeeDTO.getEmployeeRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.info(e.getMessage());
            return new ResponseEntity<>("invalid employee role. Accepted role:USER, ADMIN",
                    HttpStatus.BAD_REQUEST);
        }
        try {
            Employee newEmployee = EmployeeConverter.convertToEntity(employeeDTO);
            Employee insertedEmployee = employeeService.insertEmployee(newEmployee);
            return new ResponseEntity<>(EmployeeConverter.convertToEmployeeWithPassDTO(insertedEmployee), HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePasswordEmployee(@RequestBody @Valid EmployeeNewPassDTO employeeNewPassDTO) {
        if (employeeNewPassDTO.getEmployeeNewPass().equals(employeeNewPassDTO.getEmployeeOldPass())) {
            return new ResponseEntity<>("old password and the new one are the same", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(employeeService.updatePasswordEmployee(employeeNewPassDTO), HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") int employeeId) {
        try {
            return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
