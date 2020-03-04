package com.arobs.project.dto.employee;

import javax.validation.constraints.NotNull;

public class EmployeeNewPassDTO {


    @NotNull
    private int employeeId;

    @NotNull
    private String employeeOldPass;

    @NotNull
    private String employeeNewPass;

    public EmployeeNewPassDTO(@NotNull int employeeId, @NotNull String employeeOldPass, @NotNull String employeeNewPass) {
        this.employeeId = employeeId;
        this.employeeOldPass = employeeOldPass;
        this.employeeNewPass = employeeNewPass;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
