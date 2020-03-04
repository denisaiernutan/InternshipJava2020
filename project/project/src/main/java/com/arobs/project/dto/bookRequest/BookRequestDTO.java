package com.arobs.project.dto.bookRequest;


import com.arobs.project.dto.employee.EmployeeIdDTO;

public class BookRequestDTO {


    private EmployeeIdDTO employeeIdDTO;

    private String bookTitle;

    private String bookAuthor;

    private String publishingCompany;

    private String onlineLibrary;

    private int no_copies;

    private Double total_cost;

    private String bookReqStatus;

    public BookRequestDTO(EmployeeIdDTO employeeIdDTO, String bookTitle, String bookAuthor, String publishingCompany, String onlineLibrary, int no_copies, Double total_cost, String bookReqStatus) {

        this.employeeIdDTO = employeeIdDTO;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publishingCompany = publishingCompany;
        this.onlineLibrary = onlineLibrary;
        this.no_copies = no_copies;
        this.total_cost = total_cost;
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

    public int getNo_copies() {
        return no_copies;
    }

    public void setNo_copies(int no_copies) {
        this.no_copies = no_copies;
    }

    public Double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Double total_cost) {
        this.total_cost = total_cost;
    }

    public String getBookReqStatus() {
        return bookReqStatus;
    }

    public void setBookReqStatus(String bookReqStatus) {
        this.bookReqStatus = bookReqStatus;
    }
}
