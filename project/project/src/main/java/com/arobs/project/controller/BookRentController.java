package com.arobs.project.controller;

import com.arobs.project.converter.BookRentConverter;
import com.arobs.project.dto.bookRent.BookRentInsertDTO;
import com.arobs.project.entity.BookRent;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bookrents")
public class BookRentController {

    private BookRentService bookRentService;

    @Autowired
    public BookRentController(BookRentService bookRentService) {
        this.bookRentService = bookRentService;
    }

    @PostMapping
    public ResponseEntity<?> insertBookRent(@RequestBody BookRentInsertDTO bookRentInsertDTO) {
        try {
            BookRent bookRent = BookRentConverter.convertToEntity(bookRentInsertDTO);
            BookRent inserted = bookRentService.insertBookRent(bookRent);
            return new ResponseEntity<>(BookRentConverter.convertToBookRentWithIdDTO(inserted), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




}
