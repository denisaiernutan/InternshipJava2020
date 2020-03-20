package com.arobs.project.service;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookRent;
import com.arobs.project.entity.Copy;
import com.arobs.project.entity.Employee;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface BookRentService {

    BookRent tryToMakeBookRent(BookRent bookRent) throws ValidationException;

    void insertBookRent(BookRent bookRent, Copy copy, Employee employee, Book book);

    BookRent findById(int bookRentId);

    BookRent askForExtensionOfRental(int bookRentId) throws ValidationException;

    void markBookRentAsLate();


}
