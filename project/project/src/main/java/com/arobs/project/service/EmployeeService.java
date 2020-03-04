package com.arobs.project.service;

import com.arobs.project.dto.employee.EmployeeDTO;
import com.arobs.project.dto.employee.EmployeeNewPassDTO;
import com.arobs.project.dto.employee.EmployeeWithPassDTO;
import com.arobs.project.entity.Employee;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO insertEmployee(EmployeeWithPassDTO employeeWithPassDTO) throws ValidationException;

    EmployeeWithPassDTO updatePasswordEmployee(EmployeeNewPassDTO employeeNewPassDTO) throws ValidationException;

    boolean deleteEmployee(int employeeId) throws ValidationException;

    Employee findById(int employeeId);
}
