package com.arobs.project.dto.book;

public class BookIdDTO {

    private int bookId;

    public BookIdDTO(int bookId) {
        this.bookId = bookId;
    }

    public BookIdDTO() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
