package com.arobs.project.service.impl;

import com.arobs.project.entity.BookRequest;
import com.arobs.project.entity.Employee;
import com.arobs.project.enums.BookRequestStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.BookRequestRepository;
import com.arobs.project.service.BookRequestService;
import com.arobs.project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookReqServiceImpl implements BookRequestService {

    private BookRequestRepository bookRequestRepository;
    private EmployeeService employeeService;

    @Autowired
    public BookReqServiceImpl(BookRequestRepository bookRequestRepository, EmployeeService employeeService) {
        this.bookRequestRepository = bookRequestRepository;
        this.employeeService = employeeService;
    }

    @Override
    @Transactional
    public BookRequest insertBookRequest(BookRequest bookRequest) throws ValidationException {

        Employee employee = employeeService.findById(bookRequest.getEmployee().getEmployeeId());
        if (employee == null) {
            throw new ValidationException(" employee id invalid ");
        }
        bookRequest.setBookReqStatus(BookRequestStatus.PENDING.toString());
        bookRequest.setEmployee(employee);
        return bookRequestRepository.insertBookRequest(bookRequest);
    }


    @Override
    @Transactional
    public BookRequest updateBookRequest(BookRequest bookRequest) throws ValidationException {
        BookRequest foundBookRequest = bookRequestRepository.findById(bookRequest.getBookReqId());
        if (foundBookRequest == null) {
            throw new ValidationException("id invalid");
        }
        return bookRequestRepository.updateBookRequest(bookRequest);
    }

    @Override
    @Transactional
    public boolean deleteBookRequest(int bookReqId) {
        BookRequest bookRequest = bookRequestRepository.findById(bookReqId);
        if (bookRequest != null) {
            return bookRequestRepository.deleteBookRequest(bookRequest);
        }
        return false;
    }

    @Override
    @Transactional
    public List<BookRequest> findAll() {
        return bookRequestRepository.findAll();
    }
}
