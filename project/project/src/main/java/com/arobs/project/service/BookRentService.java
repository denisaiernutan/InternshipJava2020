package com.arobs.project.service;

import com.arobs.project.entity.BookRent;
import com.arobs.project.exception.ValidationException;
import org.springframework.transaction.annotation.Transactional;

public interface BookRentService {

    BookRent insertBookRent(BookRent bookRent) throws ValidationException;

    BookRent returnBook(BookRent bookRent) throws ValidationException;
}
