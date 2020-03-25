package com.arobs.project.controller;

import com.arobs.project.converter.BookRentConverter;
import com.arobs.project.dto.bookRent.BookRentReturnDTO;
import com.arobs.project.entity.BookRent;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/bookrents")
public class BookRentController {

    private BookRentService bookRentService;
    private static final Logger logger = LoggerFactory.getLogger("FILE");

    @Autowired
    public BookRentController(BookRentService bookRentService) {
        this.bookRentService = bookRentService;
    }


    @PostMapping
    public ResponseEntity<?> insertBookRent(@RequestParam int bookId, @RequestParam int employeeId) {
        try {
            BookRent inserted = bookRentService.tryToMakeBookRent(bookId, employeeId);
            return new ResponseEntity<>(BookRentConverter.convertToBookRentWithIdDTO(inserted), HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity<?> returnBook(@RequestBody @Valid BookRentReturnDTO bookRentReturnDTO) {
        try {
            if (bookRentReturnDTO.getGrade() > 5 || bookRentReturnDTO.getGrade() < 1)
                return new ResponseEntity<>("you have to give a grade from 1 to 5 ", HttpStatus.NOT_ACCEPTABLE);
            BookRent bookRent = BookRentConverter.convertToEntity(bookRentReturnDTO);
            BookRent returnedBookRent = bookRentService.returnBook(bookRent);
            return new ResponseEntity<>(BookRentConverter.convertToBookRentWithIdDTO(returnedBookRent), HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/extensionrent")
    public ResponseEntity<?> askForExtensionOfRental(@RequestParam int bookRentId) {
        try {
            BookRent bookRent = bookRentService.askForExtensionOfRental(bookRentId);
            return new ResponseEntity<>(BookRentConverter.convertToSimpleDTO(bookRent), HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
