package com.arobs.project.controller;

import com.arobs.project.dto.BookDTO;
import com.arobs.project.service.BookService;
import com.arobs.project.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private BookServiceImpl bookServiceImpl;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookServiceImpl = bookService;
    }

    @PostMapping
    public BookDTO insertBook(@RequestBody  BookDTO bookDTO) {
        return bookServiceImpl.insertBook(bookDTO);
    }

}
