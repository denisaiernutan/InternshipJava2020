package com.arobs.project.service.impl;

import com.arobs.project.converter.BookRequestConverter;
import com.arobs.project.dto.bookRequest.BookReqUpdateDTO;
import com.arobs.project.dto.bookRequest.BookReqWithIdDTO;
import com.arobs.project.dto.bookRequest.BookRequestDTO;
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
import java.util.stream.Collectors;

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
    public BookReqWithIdDTO insertBookRequest(BookRequestDTO bookRequestDTO) throws ValidationException {
        if (bookRequestDTO.getBookReqStatus().toUpperCase().equals(BookRequestStatus.PENDING.toString())) {
            Employee employee = employeeService.findById(bookRequestDTO.getEmployeeIdDTO().getEmployeeId());
            if (employee == null) {
                throw new ValidationException(" employee id invalid ");
            }
            BookRequest bookRequest = BookRequestConverter.convertToEntity(bookRequestDTO);
            bookRequest.setEmployee(employee);
            return BookRequestConverter.convertToBookReqWithIdDTO(bookRequestRepository.insertBookRequest(bookRequest));
        } else {
            throw new ValidationException("status invalid! Accepted status: PENDING");
        }
    }


    @Override
    @Transactional
    public BookReqWithIdDTO updateBookRequest(BookReqUpdateDTO bookReqUpdateDTO) throws ValidationException {
        try {
            BookRequestStatus.valueOf(bookReqUpdateDTO.getBookReqStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("status invalid! Accepted status: PENDING,ACCEPTED,REJECTED");
        }

        BookRequest bookRequest = bookRequestRepository.findById(bookReqUpdateDTO.getBookReqId());
        if (bookRequest == null) {
            throw new ValidationException("id invalid");
        }

        return BookRequestConverter.convertToBookReqWithIdDTO(bookRequestRepository.updateBookRequest(BookRequestConverter.convertToEntity(bookReqUpdateDTO)));

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
    public List<BookReqWithIdDTO> findAll() {
        return bookRequestRepository.findAll().stream().map(BookRequestConverter::convertToBookReqWithIdDTO).collect(Collectors.toList());
    }
}
