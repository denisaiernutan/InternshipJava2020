package com.arobs.project.dto.bookRent;

import com.arobs.project.dto.copy.CopyWithoutBookDTO;

import java.sql.Date;

public class BookRentWithIdDTO {

    private int bookRentId;

    private int book;

    private CopyWithoutBookDTO copy;

    private int employee;

    private Date rentalDate;

    private Date returnDate;

    private String bookRentStatus;

    private Double grade;

    public BookRentWithIdDTO(int bookRentId, int book, CopyWithoutBookDTO copy, int employee,
                             Date rentalDate, Date returnDate, String bookRentStatus, Double grade) {
        this.bookRentId = bookRentId;
        this.book = book;
        this.copy = copy;
        this.employee = employee;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.bookRentStatus = bookRentStatus;
        this.grade = grade;
    }

    public BookRentWithIdDTO(int bookRentId, Date rentalDate, Date returnDate, String bookRentStatus, Double grade) {
        this.bookRentId = bookRentId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.bookRentStatus = bookRentStatus;
        this.grade = grade;
    }

    public BookRentWithIdDTO() {
    }

    public int getBookRentId() {
        return bookRentId;
    }

    public void setBookRentId(int bookRentId) {
        this.bookRentId = bookRentId;
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

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookRentStatus() {
        return bookRentStatus;
    }

    public void setBookRentStatus(String bookRentStatus) {
        this.bookRentStatus = bookRentStatus;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public CopyWithoutBookDTO getCopy() {
        return copy;
    }

    public void setCopy(CopyWithoutBookDTO copy) {
        this.copy = copy;
    }
}
