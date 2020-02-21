package com.arobs.project.service;

import com.arobs.project.dto.EmployeeDTO;
import com.arobs.project.dto.EmployeeNewPassDTO;
import com.arobs.project.dto.EmployeeWithPassDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO insertEmployee(EmployeeWithPassDTO employeeWithPassDTO);
    void updatePasswordEmployee(EmployeeNewPassDTO employeeNewPassDTO);
    void deleteEmployee(String employeeEmail);
}
