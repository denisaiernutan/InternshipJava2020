package com.arobs.project.service.impl;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookRent;
import com.arobs.project.entity.Copy;
import com.arobs.project.entity.Employee;
import com.arobs.project.enums.BookRentStatus;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.BookRentRepository;
import com.arobs.project.service.BookRentService;
import com.arobs.project.service.BookService;
import com.arobs.project.service.CopyService;
import com.arobs.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;


@Service
public class BookRentServiceImpl implements BookRentService {

    private BookRentRepository bookRentRepository;
    private BookService bookService;
    private EmployeeService employeeService;
    private CopyService copyService;


    @Autowired
    public BookRentServiceImpl(BookRentRepository bookRentRepository, BookService bookService,
                               EmployeeService employeeService, CopyService copyService) {
        this.bookRentRepository = bookRentRepository;
        this.bookService = bookService;
        this.employeeService = employeeService;
        this.copyService = copyService;
    }

    @Override
    @Transactional
    public BookRent insertBookRent(BookRent bookRent) throws ValidationException {
        {
            Employee employee = employeeService.findById(bookRent.getEmployee().getEmployeeId());

            if (bookService.existBookInDb(bookRent.getBook().getBookId())) {
                Book book = bookService.findById(bookRent.getBook().getBookId());
                bookRent.setBook(book);
            } else {
                throw new ValidationException("book id invalid");
            }

            if (employee != null) {
                Copy copy = copyService.findAvailableCopiesForBook(bookRent.getBook().getBookId()).get(0);
                bookRent.setCopy(copy);
                bookRent.setGrade(0.0);
                bookRent.setBookRentStatus(BookRentStatus.ON_GOING.toString().toLowerCase());
                bookRent.setRentalDate(new Date(new java.util.Date().getTime()));
                bookRent.setEmployee(employee);
                bookRentRepository.insertBookRent(bookRent);
                updateRentedCopy(copy);
                return bookRent;
            }
            throw new ValidationException("employee id invalid");
        }

    }

    private void updateRentedCopy(Copy copy) throws ValidationException {
        copy.setCopyStatus(CopyStatus.RENTED.toString().toLowerCase());
        copyService.updateCopy(copy);

    }
}
