package com.arobs.project.controller;

import com.arobs.project.dto.bookRequest.BookReqUpdateDTO;
import com.arobs.project.dto.bookRequest.BookRequestDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookRequestController {

    private BookRequestService bookRequestService;

    @Autowired
    public BookRequestController(BookRequestService bookRequestService) {
        this.bookRequestService = bookRequestService;
    }

    @PostMapping
    public ResponseEntity<?> insertBookRequest(@RequestBody BookRequestDTO bookRequestDTO) {
        try {
            BookRequestDTO bookRequestDTOReturn = bookRequestService.insertBookRequest(bookRequestDTO);
            return new ResponseEntity<>(bookRequestDTOReturn, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(bookRequestService.findAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateBookRequest(@RequestBody BookReqUpdateDTO bookReqUpdateDTO) {
        try {
            return new ResponseEntity<>(bookRequestService.updateBookRequest(bookReqUpdateDTO), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBookRequest(@RequestParam int bookReqId) {
        return new ResponseEntity<>(bookRequestService.deleteBookRequest(bookReqId), HttpStatus.OK);
    }
}
