package com.arobs.project.repository;

import com.arobs.project.entity.Employee;

import java.util.List;

public interface EmployeeRepository {


    List<Employee> findAll();

    Employee insertEmployee(Employee employee);

    String getPasswordById(int employeeId);

    Employee updatePassword(int employeeId, String employeePass);

   List<Employee> findByEmail(String employeeEmail);

    boolean deleteById(int employeeId);

    Employee findById(int employeeId);


}
