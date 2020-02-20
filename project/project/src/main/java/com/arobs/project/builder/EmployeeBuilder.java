package com.arobs.project.builder;

import com.arobs.project.dto.EmployeeDTO;
import com.arobs.project.dto.EmployeeWithPassDTO;
import com.arobs.project.entity.Employee;
import org.modelmapper.ModelMapper;

public class EmployeeBuilder {

    private static ModelMapper modelMapper = new ModelMapper();

    public static EmployeeDTO convertToDTO(Employee employee){
        EmployeeDTO employeeDTO= modelMapper.map(employee,EmployeeDTO.class);
        return employeeDTO;
    }

    public static Employee convertToEntity(EmployeeWithPassDTO employeeWithPassDTO){
        Employee employee= modelMapper.map(employeeWithPassDTO,Employee.class);
        return employee;
    }

}
