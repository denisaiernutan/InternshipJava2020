package com.arobs.project.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EmployeeNewPassDTO {

    @NotNull
    private String employeeEmail;
    @NotNull
    private String employeeOldPass;
    @NotNull
    private String employeeNewPass;

    public EmployeeNewPassDTO(@NotNull String employeeEmail, @NotNull String employeeOldPass, @NotNull String employeeNewPass) {
        this.employeeEmail = employeeEmail;
        this.employeeOldPass = employeeOldPass;
        this.employeeNewPass = employeeNewPass;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeOldPass() {
        return employeeOldPass;
    }

    public void setEmployeeOldPass(String employeeOldPass) {
        this.employeeOldPass = employeeOldPass;
    }

    public String getEmployeeNewPass() {
        return employeeNewPass;
    }

    public void setEmployeeNewPass(String employeeNewPass) {
        this.employeeNewPass = employeeNewPass;
    }
}
