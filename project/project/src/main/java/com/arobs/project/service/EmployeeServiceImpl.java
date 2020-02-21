package com.arobs.project.service;

import com.arobs.project.converter.EmployeeConverter;
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

    private EmployeeJDBCRepository employeeJDBCRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeJDBCRepository employeeJDBCRepository) {
        this.employeeJDBCRepository = employeeJDBCRepository;
    }

    public List<EmployeeDTO> getAllEmployees() {

        List<Employee> employeeList = employeeJDBCRepository.findAll();
        return employeeList.stream()
                .map(EmployeeConverter::convertToEmployeeDTO)
                .collect(Collectors.toList());

    }

    public EmployeeDTO insertEmployee(EmployeeWithPassDTO employeeWithPassDTO) {
        Employee employee = employeeJDBCRepository.insertEmployee(EmployeeConverter.convertToEntity(employeeWithPassDTO));
        return EmployeeConverter.convertToEmployeeDTO(employee);

    }


    public void updatePasswordEmployee(EmployeeNewPassDTO employeeNewPassDTO) {
        String oldPassword = employeeJDBCRepository.getPasswordByEmail(employeeNewPassDTO.getEmployeeEmail());
        String oldPassFromEmpl = encryptPass(employeeNewPassDTO.getEmployeeOldPass());

        if (oldPassword.equals(oldPassFromEmpl)) {
            employeeJDBCRepository.updatePassword(employeeNewPassDTO.getEmployeeEmail(), employeeNewPassDTO.getEmployeeNewPass());
        } else {
            System.out.println(oldPassFromEmpl + "\n" + oldPassword);
        }

    }

    public void deleteEmployee(String employeeEmail) {
        int size = employeeEmail.length();
        String email = employeeEmail.substring(1, size - 1);
        employeeJDBCRepository.deleteByEmail(email);
    }

    private String encryptPass(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md != null;
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    //replaced with @Email in EmployeeWithPassDTO
//    private static boolean isValidEmailAddress(String email) {
//        boolean result = true;
//        try {s
//            InternetAddress emailAddr = new InternetAddress(email);
//            emailAddr.validate();
//        } catch (AddressException ex) {
//            result = false;
//        }
//        return result;
//    }
}



