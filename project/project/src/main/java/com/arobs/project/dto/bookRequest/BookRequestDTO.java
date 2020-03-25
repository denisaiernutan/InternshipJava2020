package com.arobs.project.dto.bookRequest;


import javax.validation.constraints.NotNull;

public class BookRequestDTO {


    @NotNull
    private int employeeId;

    @NotNull
    private String bookTitle;

    @NotNull
    private String bookAuthor;

    @NotNull
    private String publishingCompany;

    @NotNull
    private String onlineLibrary;

    @NotNull
    private int noCopies;

    @NotNull
    private Double totalCost;


    public BookRequestDTO(int employeeId, String bookTitle, String bookAuthor, String publishingCompany,
                          String onlineLibrary, int noCopies, Double totalCost) {

        this.employeeId = employeeId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publishingCompany = publishingCompany;
        this.onlineLibrary = onlineLibrary;
        this.noCopies = noCopies;
        this.totalCost = totalCost;
    }

    public BookRequestDTO() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

}
