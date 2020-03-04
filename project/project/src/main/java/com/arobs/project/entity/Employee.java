package com.arobs.project.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_pass")
    private String employeePass;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(name = "employee_role")
    private String employeeRole;

    @OneToMany(mappedBy = "employee")
    private Set<RentRequest> rentRequestSet = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<BookRent> bookRentSet = new HashSet<>();

    public Employee() {
    }

    public Employee(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Employee(int employeeId, String employeeName, String employeePass, String employeeEmail, String employeeRole) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeePass = employeePass;
        this.employeeEmail = employeeEmail;
        this.employeeRole = employeeRole;
    }


    public Employee(String employeeName, String employeePass, String employeeEmail, String employeeRole) {
        this.employeeName = employeeName;
        this.employeePass = employeePass;
        this.employeeEmail = employeeEmail;
        this.employeeRole = employeeRole;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public void addRentRequest(RentRequest rentRequest) {
        this.rentRequestSet.add(rentRequest);
        rentRequest.setEmployee(this);
    }

    public Set<RentRequest> getRentRequestSet() {
        return rentRequestSet;
    }

    public void setRentRequestSet(Set<RentRequest> rentRequestSet) {
        this.rentRequestSet = rentRequestSet;
    }

    public Set<BookRent> getBookRentSet() {
        return bookRentSet;
    }

    public void setBookRentSet(Set<BookRent> bookRentSet) {
        this.bookRentSet = bookRentSet;
    }
}
