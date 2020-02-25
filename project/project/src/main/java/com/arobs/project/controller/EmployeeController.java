package com.arobs.project.controller;

import com.arobs.project.dto.EmployeeDTO;
import com.arobs.project.dto.EmployeeNewPassDTO;
import com.arobs.project.dto.EmployeeWithPassDTO;
import com.arobs.project.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public EmployeeDTO insertEmployee(@RequestBody @Valid EmployeeWithPassDTO employeeDTO) {
        return employeeService.insertEmployee(employeeDTO);
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
