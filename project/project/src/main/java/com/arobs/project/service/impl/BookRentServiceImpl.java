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
import com.arobs.project.utils.UtilDate;
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
    public BookRentServiceImpl(BookRentRepository bookRentRepository, BookService bookService,
                               EmployeeService employeeService, CopyService copyService) {
        this.bookRentRepository = bookRentRepository;
        this.bookService = bookService;
        this.employeeService = employeeService;
        this.copyService = copyService;

    }

    @Override
    @Transactional
    public BookRent tryToMakeBookRent(BookRent bookRent) throws ValidationException {
        {
            Employee employee = employeeService.findById(bookRent.getEmployee().getEmployeeId());
            Book book;
            if (bookService.existBookInDb(bookRent.getBook().getBookId())) {
                book = bookService.findById(bookRent.getBook().getBookId());
            } else {
                throw new ValidationException("book id invalid");
            }

            if (employee != null && !employee.isBanned()) {
                List<Copy> copyList = copyService.findCopiesForBookByStatus(bookRent.getBook().getBookId(), CopyStatus.AVAILABLE);
                if (copyList.isEmpty()) {
                    throw new ValidationException("there is no copy available. You can register to read this book " +
                            "when it`s available ");
                } else {
                    Copy copy = copyList.get(0);
                    insertBookRent(bookRent, copy, employee, book);
                    return bookRent;
                }
            }
            throw new ValidationException("employee id invalid or employee is banned");
        }

    }

    @Override
    public void insertBookRent(BookRent bookRent, Copy copy, Employee employee, Book book) {
        int RENTAL_PERIOD = 1;

        bookRent.setBook(book);
        bookRent.setCopy(copy);
        bookRent.setEmployee(employee);
        bookRent.setGrade(0.0);
        bookRent.setBookRentStatus(BookRentStatus.ON_GOING.toString());
        bookRent.setRentalDate(new Date(new java.util.Date().getTime()));
        Date returnDate = UtilDate.addMonths(new Date(new java.util.Date().getTime()), RENTAL_PERIOD);
        bookRent.setReturnDate(returnDate);
        bookRentRepository.insertBookRent(bookRent);
        copy.setCopyStatus(CopyStatus.RENTED.toString());
    }


    @Override
    @Transactional
    public BookRent findById(int bookRentId) {
        return bookRentRepository.findById(bookRentId);
    }


    @Override
    @Transactional
    public List<BookRent> findBookRentThatPassedReturnDate() {
        return bookRentRepository.findBookRentThatPassedReturnDate();
    }

    @Override
    @Transactional
    public BookRent askForExtensionOfRental(int bookRentId) throws ValidationException {
        int MAX_RENTAL_PERIOD = 3;
        int EXTENSION_OF_RENTAL = 15;
        BookRent bookRent = bookRentRepository.findById(bookRentId);
        Date maxReturnDate = UtilDate.addMonths(bookRent.getRentalDate(), MAX_RENTAL_PERIOD);
        Date askedReturnDate = UtilDate.addDays(bookRent.getReturnDate(), EXTENSION_OF_RENTAL);

        if (askForExtensionOfRentalIsNotValid(maxReturnDate, bookRent.getReturnDate())) {
            throw new ValidationException("You may keep a book only 3 months");
        }

        if (maxReturnDate.getTime() > askedReturnDate.getTime()) {
            bookRent.setReturnDate(askedReturnDate);
        } else {
            bookRent.setReturnDate(maxReturnDate);
        }
        bookRent.getEmployee().setBanned(false);
        bookRent.setBookRentStatus(BookRentStatus.ON_GOING.toString());
        return bookRent;
    }


    private boolean askForExtensionOfRentalIsNotValid(Date maxReturnDate, Date actualReturnDate) {
        return maxReturnDate.equals(actualReturnDate);
    }
}

