package com.arobs.project.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Column(name = "sent_email_date")
    private Timestamp sentEmailDate;

    public RentRequest() {
    }

    public RentRequest(int rentReqId, Employee employee, Book book, Timestamp requestDate, String rentReqStatus) {
        this.rentReqId = rentReqId;
        this.employee = employee;
        this.book = book;
        this.requestDate = requestDate;
        this.rentReqStatus = rentReqStatus;
    }

    public RentRequest(Employee employee, Book book, Timestamp requestDate, String rentReqStatus) {
        this.employee = employee;
        this.book = book;
        this.requestDate = requestDate;
        this.rentReqStatus = rentReqStatus;
    }

    public RentRequest(int rentReqId, Employee employee, Book book, Timestamp requestDate, String rentReqStatus,
                       Timestamp sentEmailDate) {
        this.rentReqId = rentReqId;
        this.employee = employee;
        this.book = book;
        this.requestDate = requestDate;
        this.rentReqStatus = rentReqStatus;
        this.sentEmailDate = sentEmailDate;
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

    public Timestamp getSentEmailDate() {
        return sentEmailDate;
    }

    public void setSentEmailDate(Timestamp sentEmailDate) {
        this.sentEmailDate = sentEmailDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentRequest)) return false;
        RentRequest that = (RentRequest) o;
        return getRentReqId() == that.getRentReqId() &&
                Objects.equals(getEmployee(), that.getEmployee()) &&
                Objects.equals(getBook(), that.getBook()) &&
                Objects.equals(getRequestDate(), that.getRequestDate()) &&
                Objects.equals(getRentReqStatus(), that.getRentReqStatus()) &&
                Objects.equals(getSentEmailDate(), that.getSentEmailDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRentReqId(), getEmployee(), getBook(), getRequestDate(), getRentReqStatus(),
                getSentEmailDate());
    }

    @Override
    public String toString() {
        return "RentRequest{" +
                "rentReqId=" + rentReqId +
                ", employee=" + employee +
                ", book=" + book +
                ", requestDate=" + requestDate +
                ", rentReqStatus='" + rentReqStatus + '\'' +
                ", sentEmailDate=" + sentEmailDate +
                '}';
    }
}
