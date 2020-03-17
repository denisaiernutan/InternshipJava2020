package com.arobs.project.service.impl;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Employee;
import com.arobs.project.entity.RentRequest;
import com.arobs.project.enums.RentReqStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.RentRequestRepository;
import com.arobs.project.service.BookService;
import com.arobs.project.service.EmployeeService;
import com.arobs.project.service.RentRequestService;
import com.arobs.project.service.ValidationRent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class RentReqServiceImpl implements RentRequestService {

    private RentRequestRepository rentRequestRepository;

    private BookService bookService;

    private EmployeeService employeeService;

    private ValidationRent validationRent;


    @Autowired
    public RentReqServiceImpl(RentRequestRepository rentRequestRepository, BookService bookService,
                              EmployeeService employeeService, ValidationRent validationRent) {
        this.rentRequestRepository = rentRequestRepository;
        this.bookService = bookService;
        this.employeeService = employeeService;
        this.validationRent = validationRent;

    }


    @Override
    @Transactional
    public RentRequest insertRentRequest(int bookId, int employeeId) throws ValidationException {
        validationRent.validateRent(bookId, employeeId);
        Book book = bookService.findById(bookId);
        Employee employee = employeeService.findById(employeeId);
        RentRequest rentRequest = new RentRequest(employee, book, new Timestamp(System.currentTimeMillis()),
                RentReqStatus.WAITING_FOR_AVAILABLE_COPY.toString());
        return rentRequestRepository.insertRentRequest(rentRequest);
    }

    @Override
    public List<RentRequest> findByBookByStatus(int bookId, RentReqStatus rentReqStatus) {
        return rentRequestRepository.findByBookByStatus(bookId, rentReqStatus);
    }


    @Override
    @Transactional
    public RentRequest findById(int rentRequestId) {
        return rentRequestRepository.findById(rentRequestId);
    }

    @Override
    @Transactional
    public List<RentRequest> findRentReqWaitForConfirmationEarlierThan(Date date) {
        return rentRequestRepository.findWaitForConfirmationEarlierThan(date);
    }
}
