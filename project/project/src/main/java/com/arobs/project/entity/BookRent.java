package com.arobs.project.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "book_rents")
public class BookRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_rent_id")
    private int bookRentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "rental_date")
    private Date rentalDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "book_rent_status")
    private String bookRentStatus;

    @Column(name = "grade")
    private Double grade;

    public BookRent() {
    }

    public BookRent(Book book, Copy copy, Employee employee, Date rentalDate, String bookRentStatus) {
        this.book = book;
        this.copy = copy;
        this.employee = employee;
        this.rentalDate = rentalDate;
        this.bookRentStatus = bookRentStatus;
    }

    public BookRent(int bookRentId, Book book, Copy copy, Employee employee, Date rentalDate, Date returnDate, String bookRentStatus, Double grade) {
        this.bookRentId = bookRentId;
        this.book = book;
        this.copy = copy;
        this.employee = employee;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.bookRentStatus = bookRentStatus;
        this.grade = grade;
    }


    public int getBookRentId() {
        return bookRentId;
    }

    public void setBookRentId(int bookRentId) {
        this.bookRentId = bookRentId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookRent)) return false;
        BookRent bookRent = (BookRent) o;
        return getBookRentId() == bookRent.getBookRentId() &&
                Objects.equals(getBook(), bookRent.getBook()) &&
                Objects.equals(getCopy(), bookRent.getCopy()) &&
                Objects.equals(getEmployee(), bookRent.getEmployee()) &&
                Objects.equals(getRentalDate(), bookRent.getRentalDate()) &&
                Objects.equals(getReturnDate(), bookRent.getReturnDate()) &&
                Objects.equals(getBookRentStatus(), bookRent.getBookRentStatus()) &&
                Objects.equals(getGrade(), bookRent.getGrade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBookRentId(), getBook(), getCopy(), getEmployee(), getRentalDate(), getReturnDate(),
                getBookRentStatus(), getGrade());
    }

    @Override
    public String toString() {
        return "BookRent{" +
                "bookRentId=" + bookRentId +
                ", book=" + book +
                ", copy=" + copy +
                ", employee=" + employee +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", bookRentStatus='" + bookRentStatus + '\'' +
                ", grade=" + grade +
                '}';
    }
}
