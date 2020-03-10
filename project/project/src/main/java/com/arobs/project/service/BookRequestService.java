package com.arobs.project.service;

import com.arobs.project.entity.BookRequest;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface BookRequestService {

    BookRequest insertBookRequest(BookRequest bookRequest) throws ValidationException;

    BookRequest updateBookRequest(BookRequest bookRequest) throws ValidationException;

    boolean deleteBookRequest(int bookReqId);

    List<BookRequest> findAll();

}
