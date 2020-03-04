package com.arobs.project.repository;

import com.arobs.project.entity.BookRequest;

import java.util.List;

public interface BookRequestRepository  {

    BookRequest insertBookRequest(BookRequest bookRequest);

    List<BookRequest> findAll();

    BookRequest updateBookRequest(BookRequest bookRequest);

    boolean deleteBookRequest(BookRequest bookRequest);

    BookRequest findById(int bookReqId);
}


