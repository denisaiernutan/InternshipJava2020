package com.arobs.project.dto.bookRent;

public class BookRentReturnDTO {

    private int bookRentId;

    private Double grade;

    public BookRentReturnDTO() {
    }

    public BookRentReturnDTO(int bookRentId, Double grade) {
        this.bookRentId = bookRentId;
        this.grade = grade;
    }

    public int getBookRentId() {
        return bookRentId;
    }

    public void setBookRentId(int bookRentId) {
        this.bookRentId = bookRentId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
}
