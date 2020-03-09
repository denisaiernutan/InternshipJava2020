package com.arobs.project.controller;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.converter.CopyConverter;
import com.arobs.project.dto.book.BookDTO;
import com.arobs.project.dto.book.BookUpdateDTO;
import com.arobs.project.dto.book.BookWithIdDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> insertBook(@RequestBody BookDTO bookDTO) {
        try {
            Book book = BookConverter.convertToEntity(bookDTO);
            return new ResponseEntity<>(BookConverter.convertToBookWithIdDTO(bookService.insertBook(book)), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(bookService.findAll()
                .stream()
                .map(BookConverter::convertToBookWithIdDTO)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody BookUpdateDTO bookDTO) {
        Book book = bookService.updateBook(BookConverter.convertToEntity(bookDTO));
        if (book != null) {
            BookWithIdDTO bookWithIdDTO = BookConverter.convertToBookWithIdDTO(book);
            return new ResponseEntity<>(bookWithIdDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("invalid id", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public boolean deleteBook(@RequestParam int bookId) {
        return bookService.deleteBook(bookId);
    }

    @GetMapping("/findCopies")
    public ResponseEntity<?> findCopies(@RequestParam int bookId) {

        return new ResponseEntity<>(bookService.findCopies(bookId).stream().map(CopyConverter::convertToCopyWithIdDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

}