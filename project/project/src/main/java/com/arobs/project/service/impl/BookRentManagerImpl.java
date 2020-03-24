package com.arobs.project.service.impl;

import com.arobs.project.entity.*;
import com.arobs.project.enums.BookRentStatus;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.enums.RentReqStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.*;
import com.arobs.project.utils.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookRentManagerImpl implements BookRentManager {

    private RentRequestService rentRequestService;

    private CopyService copyService;

    private BookRentService bookRentService;

    private BookService bookService;


    @Autowired
    public BookRentManagerImpl(RentRequestService rentRequestService, CopyService copyService,
                               BookRentService bookRentService, BookService bookService) {
        this.rentRequestService = rentRequestService;
        this.copyService = copyService;
        this.bookRentService = bookRentService;
        this.bookService = bookService;
    }


    @Override
    @Transactional
    public BookRent returnBook(BookRent bookRent) throws ValidationException {
        BookRent updatedBookRent = bookRentService.findById(bookRent.getBook().getBookId());
        if (updatedBookRent == null) {
            throw new ValidationException("bookRent id invalid");
        }
        updateEmployeeFromBookRent(updatedBookRent);
        updatedBookRent.setReturnDate(new Date(new java.util.Date().getTime()));
        updatedBookRent.setBookRentStatus(BookRentStatus.RETURNED.toString());
        updatedBookRent.setGrade(bookRent.getGrade());
        Copy copy = updatedBookRent.getCopy();
        manageRentRequest(updatedBookRent.getBook(), copy);
        return updatedBookRent;
    }


    private void updateEmployeeFromBookRent(BookRent bookRent) {
        Employee employee = bookRent.getEmployee();
        if (employee.isBanned()) {
            Date lastDayOfBan = computeLastDayOfBanForEmployee(bookRent);
            employee.setLastDayOfBan(lastDayOfBan);
            employee.setBanned(true);
        }
    }


    private Date computeLastDayOfBanForEmployee(BookRent bookRent) {
        int MIN_BANNED_DAYS = 10;

        long exceededTimeMillis = new java.util.Date().getTime() - bookRent.getReturnDate().getTime();
        int exceededDays = (int) TimeUnit.DAYS.convert(exceededTimeMillis, TimeUnit.MILLISECONDS);
        int bannedDays = exceededDays * 2;

        Date lastDayOfBanInPresent = bookRent.getEmployee().getLastDayOfBan();
        if (lastDayOfBanInPresent == null) {
            lastDayOfBanInPresent = new Date(new java.util.Date().getTime());
        }

        if (bannedDays < MIN_BANNED_DAYS) {
            return UtilDate.addDays(lastDayOfBanInPresent, MIN_BANNED_DAYS);
        } else {
            return UtilDate.addDays(lastDayOfBanInPresent, bannedDays);
        }
    }


    @Override
    @Transactional
    public RentRequest acceptRentRequest(boolean accepted, int rentRequestId) throws ValidationException {
        RentRequest rentRequest = rentRequestService.findById(rentRequestId);
        if (rentRequest == null) {
            throw new ValidationException("invalid rent request id");
        }

        Copy copy = copyService.findCopiesForBookByStatus(rentRequest.getBook().getBookId(), CopyStatus.PENDING).get(0);
        if (accepted) {
            return confirmRentRequest(rentRequest, copy);
        }
        return declineRentRequest(rentRequest, copy);
    }

    private RentRequest confirmRentRequest(RentRequest rentRequest, Copy copy) {
        bookRentService.insertBookRent(new BookRent(), copy, rentRequest.getEmployee(), rentRequest.getBook());
        rentRequest.setRentReqStatus(RentReqStatus.GRANTED.toString());
        return rentRequest;
    }

    private RentRequest declineRentRequest(RentRequest rentRequest, Copy copy) {
        rentRequest.setRentReqStatus(RentReqStatus.DECLINED.toString());
        manageRentRequest(rentRequest.getBook(), copy);
        return rentRequest;
    }


    private void manageRentRequest(Book book, Copy copy) {
        List<RentRequest> rentRequesWaitingForCopyList = rentRequestService.findByBookByStatus(
                book.getBookId(), RentReqStatus.WAITING_FOR_AVAILABLE_COPY);

        if (rentRequesWaitingForCopyList.isEmpty()) {
            copy.setCopyStatus(CopyStatus.AVAILABLE.toString());
        } else {
            copy.setCopyStatus(CopyStatus.PENDING.toString());
            rentRequesWaitingForCopyList.sort(Comparator.comparing(RentRequest::getRequestDate));
            RentRequest rentRequest = rentRequesWaitingForCopyList.get(0);
            rentRequest.setSentEmailDate(new Timestamp(System.currentTimeMillis()));
            rentRequest.setRentReqStatus(RentReqStatus.WAITING_FOR_CONFIRMATION.toString());
        }
    }

    @Transactional
    public Copy insertAvailableCopy(Copy copy) throws ValidationException {
        Book book = bookService.findById(copy.getBook().getBookId());
        if (book == null) {
            throw new ValidationException("book id invalid");
        }
        copy.setBook(book);
        Copy newCopy = copyService.insertCopy(copy);
        manageRentRequest(book, copy);
        return newCopy;
    }
}


