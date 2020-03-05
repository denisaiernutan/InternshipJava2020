package com.arobs.project.service.impl;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookRent;
import com.arobs.project.entity.Copy;
import com.arobs.project.entity.Employee;
import com.arobs.project.enums.BookRentStatus;
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
import java.util.List;


@Service
public class BookRentServiceImpl implements BookRentService {

    private BookRentRepository bookRentRepository;
    private BookService bookService;
    private EmployeeService employeeService;
    private CopyService copyService;


    @Autowired
    public BookRentServiceImpl(BookRentRepository bookRentRepository, BookService bookService, EmployeeService employeeService, CopyService copyService) {
        this.bookRentRepository = bookRentRepository;
        this.bookService = bookService;
        this.employeeService = employeeService;
        this.copyService = copyService;
    }
    
    //try it
    @Override
    @Transactional
    public BookRent insertBookRent(BookRent bookRent) throws ValidationException {
        {
            Employee employee = employeeService.findById(bookRent.getEmployee().getEmployeeId());
            Book book = bookService.findById(bookRent.getBook().getBookId());
            if (employee != null) {
                List<Copy> availableCopies = copyService.findAvailableCopiesForBook(bookRent.getBookRentId());

                if (availableCopies == null) {
                    throw new ValidationException("book id invalid");
                }

                if (availableCopies.isEmpty()) {
                    throw new ValidationException("there is no copy available");
                }

                bookRent.setCopy(availableCopies.get(0));
                bookRent.setBookRentStatus(BookRentStatus.ON_GOING.toString().toLowerCase());
                bookRent.setRentalDate(new Date(new java.util.Date().getTime()));
                bookRent.setBook(book);
                bookRent.setEmployee(employee);

                bookRentRepository.insertBookRent(bookRent);
                return bookRent;
            }
            throw new ValidationException("employee id invalid");
        }

    }
}
