package com.arobs.project.service;

import com.arobs.project.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ValidationRent {

    private static BookService bookService;
    private static EmployeeService employeeService;

    @Autowired
    public ValidationRent(BookService bookService, EmployeeService employeeService) {
        this.bookService = bookService;
        this.employeeService = employeeService;
    }

    @Transactional
    public void validateRent(int bookId, int employeeId) throws ValidationException {

        if (bookService.existBookInDb(bookId)) {
            if (employeeService.findById(employeeId) == null) {
                throw new ValidationException("employee id invalid");
            }
        } else {
            throw new ValidationException("book id invalid");
        }
    }
}
