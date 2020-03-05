package com.arobs.project.service;

import com.arobs.project.entity.BookRent;
import com.arobs.project.exception.ValidationException;

public interface BookRentService {

    BookRent insertBookRent(BookRent bookRent) throws ValidationException;

}
