package com.arobs.project.controller;

import com.arobs.project.converter.BookRentConverter;
import com.arobs.project.dto.bookRent.BookRentInsertDTO;
import com.arobs.project.dto.bookRent.BookRentReturnDTO;
import com.arobs.project.entity.BookRent;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentManager;
import com.arobs.project.service.BookRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bookrents")
public class BookRentController {

    private BookRentService bookRentService;

    private BookRentManager bookRentManager;

    @Autowired
    public BookRentController(BookRentService bookRentService, BookRentManager bookRentManager) {
        this.bookRentService = bookRentService;
        this.bookRentManager = bookRentManager;
    }

    @PostMapping
    public ResponseEntity<?> insertBookRent(@RequestBody BookRentInsertDTO bookRentInsertDTO) {
        try {
            BookRent bookRent = BookRentConverter.convertToEntity(bookRentInsertDTO);
            BookRent inserted = bookRentService.tryToMakeBookRent(bookRent);
            return new ResponseEntity<>(BookRentConverter.convertToBookRentWithIdDTO(inserted), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> returnBook(@RequestBody BookRentReturnDTO bookRentReturnDTO) {
        try {
            BookRent bookRent = BookRentConverter.convertToEntity(bookRentReturnDTO);
            return new ResponseEntity<>(BookRentConverter.convertToBookRentWithIdDTO(bookRentManager.returnBook(bookRent)), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @PutMapping("/extensionrent")
    public ResponseEntity<?> askForExtensionOfRental(@RequestParam int bookRentId) {
        try {
            BookRent bookRent = bookRentService.askForExtensionOfRental(bookRentId);
            return new ResponseEntity<>(BookRentConverter.convertToSimpleDTO(bookRent), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
