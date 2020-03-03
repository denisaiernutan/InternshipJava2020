package com.arobs.project.dto;

public class EmployeeIdDTO {

    private int employeeId;

    public EmployeeIdDTO(int employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeeIdDTO() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
