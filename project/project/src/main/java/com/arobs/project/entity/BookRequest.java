package com.arobs.project.entity;

import javax.persistence.*;

@Entity
@Table(name = "book_requests")
public class BookRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_req_id")
    private int bookReqId;

    @ManyToOne
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
    private int no_copies;

    @Column(name = "total_cost")
    private Double total_coast;

    @Column(name = "book_req_status")
    private String bookReqStatus;

    public BookRequest() {
    }

    public BookRequest(int bookReqId, Employee employee, String bookTitle, String bookAuthor, String publishingCompany, String onlineLibrary, int no_copies, Double total_coast, String bookReqStatus) {
        this.bookReqId = bookReqId;
        this.employee = employee;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publishingCompany = publishingCompany;
        this.onlineLibrary = onlineLibrary;
        this.no_copies = no_copies;
        this.total_coast = total_coast;
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

    public int getNo_copies() {
        return no_copies;
    }

    public void setNo_copies(int no_copies) {
        this.no_copies = no_copies;
    }

    public Double getTotal_coast() {
        return total_coast;
    }

    public void setTotal_coast(Double total_coast) {
        this.total_coast = total_coast;
    }

    public String getBookReqStatus() {
        return bookReqStatus;
    }

    public void setBookReqStatus(String bookReqStatus) {
        this.bookReqStatus = bookReqStatus;
    }
}
