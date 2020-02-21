package com.arobs.project.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmployeeWithPassDTO {

    @NotNull
    private String employeeName;

    @NotNull
    private String employeePass;

    @NotNull
    @Email
    private String employeeEmail;

    @NotNull
    private String employeeRole;

    public EmployeeWithPassDTO() {
    }

    public EmployeeWithPassDTO(@NotNull String employeeName, @NotNull String employeePass, @NotNull String employeeEmail, @NotNull String employeeRole) {
        this.employeeName = employeeName;
        this.employeePass = employeePass;
        this.employeeEmail = employeeEmail;
        this.employeeRole = employeeRole;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePass() {
        return employeePass;
    }

    public void setEmployeePass(String employeePass) {
        this.employeePass = employeePass;
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
