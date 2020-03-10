package com.arobs.project.service.impl;

import com.arobs.project.converter.EmployeeConverter;
import com.arobs.project.dto.employee.EmployeeNewPassDTO;
import com.arobs.project.dto.employee.EmployeeWithPassDTO;
import com.arobs.project.entity.Employee;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.EmployeeRepository;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


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

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Transactional
    public Employee insertEmployee(Employee employee) throws ValidationException {
        if (employeeRepository.findByEmail(employee.getEmployeeEmail()).isEmpty()) {
            Employee insertedEmployee = employeeRepository.insertEmployee(employee);
            return insertedEmployee;
        } else {
            throw new ValidationException("email is already registered");
        }
    }

    @Transactional
    public EmployeeWithPassDTO updatePasswordEmployee(EmployeeNewPassDTO employeeNewPassDTO) throws ValidationException {
        String oldPassword = validateEmployee(employeeNewPassDTO);
        String oldPassFromEmpl = encryptPass(employeeNewPassDTO.getEmployeeOldPass());
        if (oldPassword.equals(oldPassFromEmpl)) {
            Employee employee = employeeRepository.updatePassword(employeeNewPassDTO.getEmployeeId(), employeeNewPassDTO.getEmployeeNewPass());
            return EmployeeConverter.convertToEmployeeWithPassDTO(employee);
        } else {
            throw new ValidationException("old password is incorrect!");
        }


    }

    public String validateEmployee(EmployeeNewPassDTO employeeNewPassDTO) throws ValidationException {
        String oldPassword;
        if (!employeeNewPassDTO.getEmployeeNewPass().equals(employeeNewPassDTO.getEmployeeOldPass())) {
            try {
                oldPassword = employeeRepository.getPasswordById(employeeNewPassDTO.getEmployeeId());
                if (oldPassword == null) {
                    throw new ValidationException("invalid id");
                } else return oldPassword;
            } catch (EmptyResultDataAccessException e) {
                throw new ValidationException("invalid id");
            }
        } else {
            throw new ValidationException("old password and the new one are the same");
        }

    }

    @Transactional
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

    @Override
    public Employee findById(int employeeId) {
        return employeeRepository.findById(employeeId);
    }
}



