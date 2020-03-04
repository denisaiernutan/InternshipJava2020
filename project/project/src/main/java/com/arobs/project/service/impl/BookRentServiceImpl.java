package com.arobs.project.service.impl;

import com.arobs.project.repository.BookRentRepository;
import com.arobs.project.service.BookRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookRentServiceImpl implements BookRentService {

    private BookRentRepository bookRentRepository;

    @Autowired
    public BookRentServiceImpl(BookRentRepository bookRentRepository) {
        this.bookRentRepository = bookRentRepository;
    }
}
