package com.arobs.project.dto;

public class EmployeeDTO {

    private String employeeName;

    private String employeeEmail;

    private String employeeRole;


    public EmployeeDTO(String employeeName, String employeePass, String employeeEmail, String employeeRole) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeeRole = employeeRole;
    }

    public EmployeeDTO() {
    }


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }


    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
}
