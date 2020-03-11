package com.arobs.project.dto.rentRequest;

import java.sql.Timestamp;

public class RentRequestDTO {

    private int employee;

    private int book;

    private Timestamp requestDate;

    private String rentReqStatus;

    public RentRequestDTO() {
    }

    public RentRequestDTO(int employee, int book, Timestamp requestDate, String rentReqStatus) {
        this.employee = employee;
        this.book = book;
        this.requestDate = requestDate;
        this.rentReqStatus = rentReqStatus;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public String getRentReqStatus() {
        return rentReqStatus;
    }

    public void setRentReqStatus(String rentReqStatus) {
        this.rentReqStatus = rentReqStatus;
    }
}
