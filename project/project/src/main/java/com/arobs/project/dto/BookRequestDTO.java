package com.arobs.project.dto;


public class BookRequestDTO {

    private int bookReqId;

    private EmployeeIdDTO employeeIdDTO;

    private String bookTitle;

    private String bookAuthor;

    private String publishingCompany;

    private String onlineLibrary;

    private int no_copies;

    private Double total_coast;

    private String bookReqStatus;

    public BookRequestDTO(int bookReqId, EmployeeIdDTO employeeIdDTO, String bookTitle, String bookAuthor, String publishingCompany, String onlineLibrary, int no_copies, Double total_coast, String bookReqStatus) {
        this.bookReqId = bookReqId;
        this.employeeIdDTO = employeeIdDTO;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publishingCompany = publishingCompany;
        this.onlineLibrary = onlineLibrary;
        this.no_copies = no_copies;
        this.total_coast = total_coast;
        this.bookReqStatus = bookReqStatus;
    }

    public BookRequestDTO() {
    }

    public int getBookReqId() {
        return bookReqId;
    }

    public void setBookReqId(int bookReqId) {
        this.bookReqId = bookReqId;
    }

    public EmployeeIdDTO getEmployeeIdDTO() {
        return employeeIdDTO;
    }

    public void setEmployeeIdDTO(EmployeeIdDTO employeeIdDTO) {
        this.employeeIdDTO = employeeIdDTO;
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
