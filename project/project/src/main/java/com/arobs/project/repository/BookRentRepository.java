package com.arobs.project.repository;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookRent;
import com.arobs.project.entity.Employee;

import java.sql.Date;
import java.util.List;

public interface BookRentRepository {

    BookRent insertBookRent(BookRent bookRent);

    BookRent findById(int bookRentId);

    List<BookRent> findBookRentThatPassedReturnDate();

    List<Book> listXBooksRented(int noOfBooks, Date startDate, Date endDate);

    List<Book> listBooksRented(Date startDate, Date endDate);

    List<Employee> listXEmployeesByNoOfBooksRead(int noOfEmployees, Date startDate, Date endDate);

    List<Employee> listLateEmployees();
}
