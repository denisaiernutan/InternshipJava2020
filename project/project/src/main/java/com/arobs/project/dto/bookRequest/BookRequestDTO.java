package com.arobs.project.dto.bookRequest;


import com.arobs.project.dto.employee.EmployeeIdDTO;

public class BookRequestDTO {


    private EmployeeIdDTO employeeIdDTO;

    private String bookTitle;

    private String bookAuthor;

    private String publishingCompany;

    private String onlineLibrary;

    private int noCopies;

    private Double totalCost;

    private String bookReqStatus;

    public BookRequestDTO(EmployeeIdDTO employeeIdDTO, String bookTitle, String bookAuthor, String publishingCompany, String onlineLibrary, int noCopies, Double totalCost, String bookReqStatus) {

        this.employeeIdDTO = employeeIdDTO;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publishingCompany = publishingCompany;
        this.onlineLibrary = onlineLibrary;
        this.noCopies = noCopies;
        this.totalCost = totalCost;
        this.bookReqStatus = bookReqStatus;
    }

    public BookRequestDTO() {
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
