package com.arobs.project.converter;

import com.arobs.project.dto.EmployeeDTO;
import com.arobs.project.dto.EmployeeWithPassDTO;
import com.arobs.project.entity.Employee;
import org.modelmapper.ModelMapper;

public class EmployeeConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static EmployeeDTO convertToEmployeeDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public static Employee convertToEntity(EmployeeWithPassDTO employeeWithPassDTO) {
        return modelMapper.map(employeeWithPassDTO, Employee.class);
    }


}
