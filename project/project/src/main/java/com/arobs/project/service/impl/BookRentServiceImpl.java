package com.arobs.project.service.impl;

import com.arobs.project.entity.*;
import com.arobs.project.enums.BookRentStatus;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.enums.RentReqStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.BookRentRepository;
import com.arobs.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;


@Service
public class BookRentServiceImpl implements BookRentService {

    private BookRentRepository bookRentRepository;
    private BookService bookService;
    private EmployeeService employeeService;
    private CopyService copyService;
    private RentRequestService rentRequestService;


    @Autowired
    public BookRentServiceImpl(BookRentRepository bookRentRepository, BookService bookService,
                               EmployeeService employeeService, CopyService copyService,
                               RentRequestService rentRequestService) {
        this.bookRentRepository = bookRentRepository;
        this.bookService = bookService;
        this.employeeService = employeeService;
        this.copyService = copyService;
        this.rentRequestService = rentRequestService;
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
                List<Copy> copyList = copyService.findAvailableCopiesForBook(bookRent.getBook().getBookId());
                if (copyList.isEmpty()) {
                    throw new ValidationException("there is no copy available. You can register to read this book " +
                            "when it`s available ");
                } else {
                    Copy copy = copyService.findAvailableCopiesForBook(bookRent.getBook().getBookId()).get(0);
                    buildNewBookRent(bookRent, copy, employee);
                    return bookRent;
                }
            }
            throw new ValidationException("employee id invalid");
        }

    }

    private void buildNewBookRent(BookRent bookRent, Copy copy, Employee employee) {
        int THIRTYDAYS = 30;

        bookRent.setCopy(copy);
        bookRent.setEmployee(employee);
        bookRent.setGrade(0.0);
        bookRent.setBookRentStatus(BookRentStatus.ON_GOING.toString());
        bookRent.setRentalDate(new Date(new java.util.Date().getTime()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, THIRTYDAYS);
        bookRent.setReturnDate(new Date(calendar.getTimeInMillis()));
        bookRentRepository.insertBookRent(bookRent);
        copy.setCopyStatus(CopyStatus.RENTED.toString());

    }

    @Override
    @Transactional
    public BookRent returnBook(BookRent bookRent) throws ValidationException {
        BookRent updatedBookRent = ValidationService.safeGetUniqueResult(bookRentRepository.findById(bookRent.getBookRentId()));
        if (updatedBookRent == null) {
            throw new ValidationException("bookRent id invalid");
        } else {
            updatedBookRent.setReturnDate(new Date(new java.util.Date().getTime()));
            updatedBookRent.setBookRentStatus(BookRentStatus.RETURNED.toString());
            updatedBookRent.setGrade(bookRent.getGrade());
            Copy copy = updatedBookRent.getCopy();
            List<RentRequest> rentRequestList = rentRequestService.findByBook(updatedBookRent.getBook().getBookId());
            if (rentRequestList.isEmpty()) {
                copy.setCopyStatus(CopyStatus.AVAILABLE.toString());
            } else {
                copy.setCopyStatus(CopyStatus.PENDING.toString());
                manageRentRequest(rentRequestList);
            }
            return updatedBookRent;
        }
    }

    private void manageRentRequest(List<RentRequest> rentRequestList) {
        rentRequestList.sort(Comparator.comparing(RentRequest::getRequestDate));
        RentRequest rentRequest = rentRequestList.get(0);
        rentRequest.setRentReqStatus(RentReqStatus.WAITING_FOR_CONFIRMATION.toString());
    }


}

