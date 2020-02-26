package com.arobs.project.repository;

import com.arobs.project.entity.Employee;

import java.util.List;

public interface EmployeeRepository {


    List<Employee> findAll();

    Employee insertEmployee(Employee employee);

    String getPasswordByEmail(String employeeEmail);

    Employee updatePassword(String employeeEmail, String employeePass);

   List<Employee> findByEmail(String employeeEmail);

    boolean deleteByEmail(String employeeEmail);


}
