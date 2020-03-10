package com.arobs.project.dto.bookRent;

public class BookRentInsertDTO {

    private int book;

    private int employee;


    public BookRentInsertDTO(int book, int employee) {
        this.book = book;
        this.employee = employee;
    }

    public BookRentInsertDTO() {
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }
}
