package com.arobs.project.dto.bookRequest;

import com.arobs.project.dto.employee.EmployeeIdDTO;

public class BookReqWithIdDTO extends BookRequestDTO {

    private int bookReqId;


    public BookReqWithIdDTO(EmployeeIdDTO employeeIdDTO, String bookTitle, String bookAuthor, String publishingCompany, String onlineLibrary, int noCopies, Double totalCost, String bookReqStatus, int bookReqId) {
        super(employeeIdDTO, bookTitle, bookAuthor, publishingCompany, onlineLibrary, noCopies, totalCost, bookReqStatus);
        this.bookReqId = bookReqId;
    }

    public BookReqWithIdDTO() {
    }

    public int getBookReqId() {
        return bookReqId;
    }

    public void setBookReqId(int bookReqId) {
        this.bookReqId = bookReqId;
    }

}
