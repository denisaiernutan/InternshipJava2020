package com.arobs.project.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "rent_requests")
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rent_req_id")
    private int rentReqId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "request_date")
    private Timestamp requestDate;

    @Column(name = "rent_req_status")
    private String rentReqStatus;

    public RentRequest() {
    }

    public RentRequest(int rentReqId, Employee employee, Book book, Timestamp requestDate, String rentReqStatus) {
        this.rentReqId = rentReqId;
        this.employee = employee;
        this.book = book;
        this.requestDate = requestDate;
        this.rentReqStatus = rentReqStatus;
    }

    public int getRentReqId() {
        return rentReqId;
    }

    public void setRentReqId(int rentReqId) {
        this.rentReqId = rentReqId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
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
