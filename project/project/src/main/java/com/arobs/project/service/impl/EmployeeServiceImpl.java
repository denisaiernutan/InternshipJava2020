package com.arobs.project.service.impl;

import com.arobs.project.converter.EmployeeConverter;
import com.arobs.project.dto.EmployeeDTO;
import com.arobs.project.dto.EmployeeNewPassDTO;
import com.arobs.project.dto.EmployeeWithPassDTO;
import com.arobs.project.entity.Employee;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.EmployeeRepository;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private RepoFactory repoFactory;

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(RepoFactory repoFactory) {
        this.repoFactory = repoFactory;
    }

    @PostConstruct
    public void init() {
        this.employeeRepository = this.repoFactory.getInstance().getEmployeeRepository();
    }

    public List<EmployeeDTO> getAllEmployees() {

        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList.stream()
                .map(EmployeeConverter::convertToEmployeeDTO)
                .collect(Collectors.toList());

    }


    public EmployeeDTO insertEmployee(EmployeeWithPassDTO employeeWithPassDTO) {
        if (employeeRepository.findByEmail(employeeWithPassDTO.getEmployeeEmail()).isEmpty()) {
            Employee employee = employeeRepository.insertEmployee(EmployeeConverter.convertToEntity(employeeWithPassDTO));
            return EmployeeConverter.convertToEmployeeDTO(employee);
        } else {
            throw new ValidationException("email is already registered");
        }
    }

    public EmployeeWithPassDTO updatePasswordEmployee(EmployeeNewPassDTO employeeNewPassDTO) {
        String oldPassword = employeeRepository.getPasswordByEmail(employeeNewPassDTO.getEmployeeEmail());
        String oldPassFromEmpl = encryptPass(employeeNewPassDTO.getEmployeeOldPass());

        if (oldPassword.equals(oldPassFromEmpl)) {
            Employee employee = employeeRepository.updatePassword(employeeNewPassDTO.getEmployeeEmail(), employeeNewPassDTO.getEmployeeNewPass());
            return EmployeeConverter.convertToEmployeeWithPassDTO(employee);
        }
        return new EmployeeWithPassDTO();

    }

    public boolean deleteEmployee(String employeeEmail) {
        return employeeRepository.deleteByEmail(employeeEmail);
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



