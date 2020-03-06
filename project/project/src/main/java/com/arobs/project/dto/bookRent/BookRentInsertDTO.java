package com.arobs.project.dto.bookRent;

import com.arobs.project.dto.book.BookIdDTO;
import com.arobs.project.dto.employee.EmployeeIdDTO;

public class BookRentInsertDTO {

    private BookIdDTO book;

    private EmployeeIdDTO employee;


    public BookRentInsertDTO(BookIdDTO book, EmployeeIdDTO employee) {
        this.book = book;
        this.employee = employee;
    }

    public BookRentInsertDTO() {
    }

    public BookIdDTO getBook() {
        return book;
    }

    public void setBook(BookIdDTO book) {
        this.book = book;
    }

    public EmployeeIdDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeIdDTO employee) {
        this.employee = employee;
    }
}
