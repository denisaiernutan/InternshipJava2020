package com.arobs.project.dto.bookRequest;

public class BookReqUpdateDTO {

    private int bookReqId;

    private String bookReqStatus;

    public BookReqUpdateDTO(int bookReqId, String bookReqStatus) {
        this.bookReqId = bookReqId;
        this.bookReqStatus = bookReqStatus;
    }

    public BookReqUpdateDTO() {
    }

    public int getBookReqId() {
        return bookReqId;
    }

    public void setBookReqId(int bookReqId) {
        this.bookReqId = bookReqId;
    }

    public String getBookReqStatus() {
        return bookReqStatus;
    }

    public void setBookReqStatus(String bookReqStatus) {
        this.bookReqStatus = bookReqStatus;
    }
}
