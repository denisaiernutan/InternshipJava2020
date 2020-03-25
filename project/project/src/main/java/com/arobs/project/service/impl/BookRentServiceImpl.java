package com.arobs.project.service.impl;

import com.arobs.project.entity.*;
import com.arobs.project.enums.BookRentStatus;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.enums.RentReqStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.BookRentRepository;
import com.arobs.project.service.*;
import com.arobs.project.utils.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


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
    public BookRent tryToMakeBookRent(int bookId, int employeeId) throws ValidationException {
        {
            Book book = bookService.findById(bookId);
            if (book == null) {
                throw new ValidationException("book id invalid");
            }
            Employee employee = employeeService.findById(employeeId);
            if (employee == null) {
                throw new ValidationException("employee id invalid");
            }
            if (employeeHasNotPermissionToRentNewBook(employee, book)) {
                throw new ValidationException("you don't have permission to rent a book");
            }

            List<Copy> copyList = copyService.findCopiesForBookByStatus(bookId,
                    CopyStatus.AVAILABLE);
            if (copyList.isEmpty()) {
                throw new ValidationException("there is no copy available. You can register to read this book " +
                        "when it`s available ");
            }
            return insertBookRent(copyList.get(0), employee, book);
        }

    }

    private boolean employeeHasNotPermissionToRentNewBook(Employee employee, Book book) {
        if (employee.isBanned()) {
            return true;
        }
        Set<BookRent> bookRentList = employee.getBookRentSet();
        for (BookRent bookRent : bookRentList) {
            if (bookRent.getBook().equals(book)
                    && bookRent.getBookRentStatus().equals(BookRentStatus.ON_GOING.toString()))
                return true;
        }
        return false;
    }


    private BookRent insertBookRent(Copy copy, Employee employee, Book book) {
        int RENTAL_PERIOD = 1;

        BookRent bookRent = new BookRent();
        bookRent.setBook(book);
        bookRent.setCopy(copy);
        bookRent.setEmployee(employee);
        bookRent.setGrade(0.0);
        bookRent.setBookRentStatus(BookRentStatus.ON_GOING.toString());
        bookRent.setRentalDate(UtilDate.getNow());
        Date returnDate = UtilDate.addMonths(UtilDate.getNow(), RENTAL_PERIOD);
        bookRent.setReturnDate(returnDate);
        copy.setCopyStatus(CopyStatus.RENTED.toString());
        return bookRentRepository.insertBookRent(bookRent);
    }


    @Override
    @Transactional
    public BookRent findById(int bookRentId) {
        return bookRentRepository.findById(bookRentId);
    }

    @Override
    @Transactional
    public BookRent askForExtensionOfRental(int bookRentId) throws ValidationException {
        int MAX_RENTAL_PERIOD = 3;
        int EXTENSION_OF_RENTAL = 15;
        BookRent bookRent = bookRentRepository.findById(bookRentId);
        if (bookRent == null) {
            throw new ValidationException("book rent id invalid");
        }

        if (askForExtensionIsNotInTheLastFiveDays(bookRent.getReturnDate(), bookRent.getBookRentStatus())) {
            throw new ValidationException("you may ask for extension in your last 5 days of rental");
        }

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

        return bookRent;
    }

    private boolean askForExtensionOfRentalIsNotValid(Date maxReturnDate, Date actualReturnDate) {
        return maxReturnDate.equals(actualReturnDate);
    }

    private boolean askForExtensionIsNotInTheLastFiveDays(Date actualReturnDate, String statusBookRent) {
        int DAYS_FOR_ASKING_EXTENSION = 5;

        Date fiveDaysBeforeActualReturnDate = UtilDate.addDays(actualReturnDate, -DAYS_FOR_ASKING_EXTENSION);
        Date today = UtilDate.getNow();
        return today.before(fiveDaysBeforeActualReturnDate) || statusBookRent.equals(BookRentStatus.LATE.toString());
    }

    @Transactional
    public void markBookRentAsLate() {
        List<BookRent> bookRentList = bookRentRepository.findBookRentThatPassedReturnDate();
        for (BookRent bookRent : bookRentList) {
            bookRent.getEmployee().setBanned(true);
            bookRent.setBookRentStatus(BookRentStatus.LATE.toString());
        }
    }

    @Override
    @Transactional
    public Copy insertAvailableCopy(int bookId) throws ValidationException {
        Book book = bookService.findById(bookId);
        if (book == null) {
            throw new ValidationException("book id invalid");
        }
        Copy copy = new Copy();
        copy.setCopyFlag(true);
        copy.setBook(book);
        Copy newCopy = copyService.insertCopy(copy);
        manageRentRequest(book, copy);
        return newCopy;
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
            rentRequest.setSentEmailDate(UtilDate.getNowTimestamp());
            rentRequest.setRentReqStatus(RentReqStatus.WAITING_FOR_CONFIRMATION.toString());
        }
    }

    @Override
    @Transactional
    public BookRent returnBook(BookRent bookRent) throws ValidationException {
        BookRent updatedBookRent = findById(bookRent.getBook().getBookId());
        if (updatedBookRent == null) {
            throw new ValidationException("bookRent id invalid");
        }
        updateEmployeeFromBookRent(updatedBookRent);
        updatedBookRent.setReturnDate(UtilDate.getNow());
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

        long exceededTimeMillis = System.currentTimeMillis() - bookRent.getReturnDate().getTime();
        int exceededDays = (int) TimeUnit.DAYS.convert(exceededTimeMillis, TimeUnit.MILLISECONDS);
        int bannedDays = exceededDays * 2;

        Date lastDayOfBanInPresent = bookRent.getEmployee().getLastDayOfBan();
        if (lastDayOfBanInPresent == null) {
            lastDayOfBanInPresent = UtilDate.getNow();
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
        insertBookRent(copy, rentRequest.getEmployee(), rentRequest.getBook());
        rentRequest.setRentReqStatus(RentReqStatus.GRANTED.toString());
        return rentRequest;
    }

    private RentRequest declineRentRequest(RentRequest rentRequest, Copy copy) {
        rentRequest.setRentReqStatus(RentReqStatus.DECLINED.toString());
        manageRentRequest(rentRequest.getBook(), copy);
        return rentRequest;
    }


}

