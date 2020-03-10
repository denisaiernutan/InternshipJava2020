package com.arobs.project.dto.bookRequest;


public class BookReqWithIdDTO extends BookRequestDTO {

    private int bookReqId;


    public BookReqWithIdDTO(int employeeId, String bookTitle, String bookAuthor, String publishingCompany,
                            String onlineLibrary, int noCopies, Double totalCost, String bookReqStatus, int bookReqId) {
        super(employeeId, bookTitle, bookAuthor, publishingCompany, onlineLibrary, noCopies, totalCost, bookReqStatus);
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
