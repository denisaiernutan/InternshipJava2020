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
import org.springframework.dao.EmptyResultDataAccessException;
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


    public EmployeeDTO insertEmployee(EmployeeWithPassDTO employeeWithPassDTO) throws ValidationException {
        if (employeeRepository.findByEmail(employeeWithPassDTO.getEmployeeEmail()).isEmpty()) {
            Employee employee = employeeRepository.insertEmployee(EmployeeConverter.convertToEntity(employeeWithPassDTO));
            return EmployeeConverter.convertToEmployeeDTO(employee);
        } else {
            throw new ValidationException("email is already registered");
        }
    }

    public EmployeeWithPassDTO updatePasswordEmployee(EmployeeNewPassDTO employeeNewPassDTO) throws ValidationException {
        String oldPassword;
        if (!employeeNewPassDTO.getEmployeeNewPass().equals(employeeNewPassDTO.getEmployeeOldPass())) {
            try {
                oldPassword = employeeRepository.getPasswordById(employeeNewPassDTO.getEmployeeId());
            } catch (EmptyResultDataAccessException e) {
                throw new ValidationException("invalid id");
            }
            String oldPassFromEmpl = encryptPass(employeeNewPassDTO.getEmployeeOldPass());
            if (oldPassword.equals(oldPassFromEmpl)) {
                Employee employee = employeeRepository.updatePassword(employeeNewPassDTO.getEmployeeId(), employeeNewPassDTO.getEmployeeNewPass());
                return EmployeeConverter.convertToEmployeeWithPassDTO(employee);
            } else {
                throw new ValidationException("old password is incorrect!");
            }
        } else {
            throw new ValidationException("old password and the new one are the same");
        }

    }

    public boolean deleteEmployee(int employeeId) throws ValidationException {
        try {
            if (employeeRepository.findById(employeeId) != null) {
                return employeeRepository.deleteById(employeeId);
            } else {
                throw new ValidationException("id is invalid!");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ValidationException("invalid id");
        }
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



