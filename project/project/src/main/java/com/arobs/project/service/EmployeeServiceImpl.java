package com.arobs.project.service;

import com.arobs.project.builder.EmployeeBuilder;
import com.arobs.project.dto.EmployeeDTO;
import com.arobs.project.dto.EmployeeNewPassDTO;
import com.arobs.project.dto.EmployeeWithPassDTO;
import com.arobs.project.entity.Employee;
import com.arobs.project.repository.EmployeeJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeJDBCRepository  employeeJDBCRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeJDBCRepository employeeJDBCRepository) {
        this.employeeJDBCRepository=employeeJDBCRepository;
    }

    public List<EmployeeDTO> getAllEmployees(){

        List<Employee> employeeList= employeeJDBCRepository.findAll();
        List<EmployeeDTO> employeeDTOList=employeeList.stream().map(employee -> EmployeeBuilder.convertToDTO(employee)).collect(Collectors.toList());

        return employeeDTOList;
    }

    public void insertEmployee(EmployeeWithPassDTO employeeWithPassDTO) {
        int newId= employeeJDBCRepository.insertEmployee(EmployeeBuilder.convertToEntity(employeeWithPassDTO));
    }


    public void updatePasswordEmployee(EmployeeNewPassDTO employeeNewPassDTO){
        String oldPassword=employeeJDBCRepository.getPasswordByEmail(employeeNewPassDTO.getEmployeeEmail());
        String oldPassFromEmpl= encryptPass(employeeNewPassDTO.getEmployeeOldPass());

        if(oldPassword.equals(oldPassFromEmpl))
        {
            employeeJDBCRepository.updatePassword(employeeNewPassDTO.getEmployeeEmail(), employeeNewPassDTO.getEmployeeNewPass());
        }
        else {
            System.out.println(oldPassFromEmpl+ "\n" + oldPassword);
        }

    }

    public void deleteEmployee(String employeeEmail){
        int size=employeeEmail.length();
        String email= employeeEmail.substring(1,size-1);
        employeeJDBCRepository.deleteByEmail(email);
    }

    private String encryptPass(String password){
        MessageDigest md= null;
        try {
            md= MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hashInBytes= md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


}
