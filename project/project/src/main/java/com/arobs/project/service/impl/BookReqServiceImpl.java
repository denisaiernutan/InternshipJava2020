package com.arobs.project.service.impl;

import com.arobs.project.repository.BookRequestRepository;
import com.arobs.project.service.BookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookReqServiceImpl implements BookRequestService {

    private BookRequestRepository bookRequestRepository;

    @Autowired
    public BookReqServiceImpl(BookRequestRepository bookRequestRepository) {
        this.bookRequestRepository = bookRequestRepository;
    }
}
