package com.arobs.project.dto.rentRequest;

import java.sql.Timestamp;

public class RentRequestWithIdDTO {

    private int rentReqId;

    private int employee;

    private int book;

    private Timestamp requestDate;

    private String rentReqStatus;

    public RentRequestWithIdDTO() {
    }

    public RentRequestWithIdDTO(int rentReqId, int employee, int book, Timestamp requestDate, String rentReqStatus) {
        this.rentReqId = rentReqId;
        this.employee = employee;
        this.book = book;
        this.requestDate = requestDate;
        this.rentReqStatus = rentReqStatus;
    }

    public RentRequestWithIdDTO(int rentReqId, Timestamp requestDate, String rentReqStatus) {
        this.rentReqId = rentReqId;
        this.requestDate = requestDate;
        this.rentReqStatus = rentReqStatus;
    }

    public int getRentReqId() {
        return rentReqId;
    }

    public void setRentReqId(int rentReqId) {
        this.rentReqId = rentReqId;
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
