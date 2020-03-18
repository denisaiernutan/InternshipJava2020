package com.arobs.project.service.impl;

import com.arobs.project.entity.*;
import com.arobs.project.enums.BookRentStatus;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.enums.RentReqStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentManager;
import com.arobs.project.service.BookRentService;
import com.arobs.project.service.CopyService;
import com.arobs.project.service.RentRequestService;
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

    @Autowired
    public BookRentManagerImpl(RentRequestService rentRequestService, CopyService copyService,
                               BookRentService bookRentService) {
        this.rentRequestService = rentRequestService;
        this.copyService = copyService;
        this.bookRentService = bookRentService;
    }


    @Override
    @Transactional
    public RentRequest confirmRentRequest(int rentRequestId) throws ValidationException {
        RentRequest rentRequest = rentRequestService.findById(rentRequestId);
        Copy copy = copyService.findCopiesForBookByStatus(rentRequest.getBook().getBookId(), CopyStatus.PENDING).get(0);
        bookRentService.insertBookRent(new BookRent(), copy, rentRequest.getEmployee(), rentRequest.getBook());
        rentRequest.setRentReqStatus(RentReqStatus.GRANTED.toString());
        return rentRequest;
    }


    @Override
    @Transactional
    public BookRent returnBook(BookRent bookRent) throws ValidationException {
        BookRent updatedBookRent = bookRentService.findById(bookRent.getBook().getBookId());
        if (updatedBookRent == null) {
            throw new ValidationException("bookRent id invalid");
        } else {
            updateEmployeeFromBookRent(updatedBookRent);
            updatedBookRent.setReturnDate(new Date(new java.util.Date().getTime()));
            updatedBookRent.setBookRentStatus(BookRentStatus.RETURNED.toString());
            updatedBookRent.setGrade(bookRent.getGrade());
            Copy copy = updatedBookRent.getCopy();

            manageRentRequest(updatedBookRent.getBook(), copy);
            return updatedBookRent;
        }
    }


    private void updateEmployeeFromBookRent(BookRent bookRent) {
        Employee employee = bookRent.getEmployee();
        if (employeeExceededThreeMonths(bookRent.getRentalDate())) {
            Date lastDayOfBan = computeLastDayOfBanForEmployee(bookRent);
            employee.setLastDayOfBan(lastDayOfBan);
            employee.setBanned(true);
        } else {
            employee.setBanned(false);
        }
    }

    private boolean employeeExceededThreeMonths(Date rentalDate) {
        int MAX_RENTAL_PERIOD = 3;
        Date maxReturnDate = UtilDate.addMonths(rentalDate, MAX_RENTAL_PERIOD);
        java.util.Date now = new java.util.Date();
        return maxReturnDate.before(now);
    }

    private Date computeLastDayOfBanForEmployee(BookRent bookRent) {

        int MIN_BANNED_DAYS = 10;
        int MAX_RENTAL_PERIOD = 3;

        Date maxReturnDate = UtilDate.addMonths(bookRent.getRentalDate(), MAX_RENTAL_PERIOD);
        java.util.Date now = new java.util.Date();

        long exceededTimeMillis = now.getTime() - maxReturnDate.getTime();
        int exceededDays = (int) TimeUnit.DAYS.convert(exceededTimeMillis, TimeUnit.MILLISECONDS);
        int bannedDays = exceededDays * 2;

        if (bannedDays < MIN_BANNED_DAYS) {
            return UtilDate.addDays(new Date(new java.util.Date().getTime()), MIN_BANNED_DAYS);
        } else {
            return UtilDate.addDays(new Date(new java.util.Date().getTime()), bannedDays);
        }
    }


    @Override
    @Transactional
    public RentRequest declineRentRequest(int rentRequestId) throws ValidationException {
        RentRequest rentRequest = rentRequestService.findById(rentRequestId);
        rentRequest.setRentReqStatus(RentReqStatus.DECLINED.toString());
        Copy copy = copyService.findCopiesForBookByStatus(rentRequest.getBook().getBookId(), CopyStatus.PENDING).get(0);
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
            rentRequest.setRequestDate(new Timestamp(System.currentTimeMillis()));
            rentRequest.setRentReqStatus(RentReqStatus.WAITING_FOR_CONFIRMATION.toString());
        }
    }

}
