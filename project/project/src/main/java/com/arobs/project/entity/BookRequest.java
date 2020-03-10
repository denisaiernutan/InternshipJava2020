package com.arobs.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "book_requests")
public class BookRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_req_id")
    private int bookReqId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "publishing_company")
    private String publishingCompany;

    @Column(name = "online_library")
    private String onlineLibrary;

    @Column(name = "no_copies")
    private int noCopies;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "book_req_status")
    private String bookReqStatus;

    public BookRequest() {
    }

    public BookRequest(int bookReqId, Employee employee, String bookTitle, String bookAuthor, String publishingCompany, String onlineLibrary, int noCopies, Double totalCost, String bookReqStatus) {
        this.bookReqId = bookReqId;
        this.employee = employee;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publishingCompany = publishingCompany;
        this.onlineLibrary = onlineLibrary;
        this.noCopies = noCopies;
        this.totalCost = totalCost;
        this.bookReqStatus = bookReqStatus;
    }


    public int getBookReqId() {
        return bookReqId;
    }

    public void setBookReqId(int bookReqId) {
        this.bookReqId = bookReqId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public String getOnlineLibrary() {
        return onlineLibrary;
    }

    public void setOnlineLibrary(String onlineLibrary) {
        this.onlineLibrary = onlineLibrary;
    }

    public int getNoCopies() {
        return noCopies;
    }

    public void setNoCopies(int noCopies) {
        this.noCopies = noCopies;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getBookReqStatus() {
        return bookReqStatus;
    }

    public void setBookReqStatus(String bookReqStatus) {
        this.bookReqStatus = bookReqStatus;
    }
}
