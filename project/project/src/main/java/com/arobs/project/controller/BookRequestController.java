package com.arobs.project.controller;

import com.arobs.project.converter.BookRequestConverter;
import com.arobs.project.dto.bookRequest.BookReqUpdateDTO;
import com.arobs.project.dto.bookRequest.BookRequestDTO;
import com.arobs.project.entity.BookRequest;
import com.arobs.project.enums.BookRequestStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/bookrequests")
public class BookRequestController {

    private static final Logger logger = LoggerFactory.getLogger("FILE");
    private BookRequestService bookRequestService;

    @Autowired
    public BookRequestController(BookRequestService bookRequestService) {
        this.bookRequestService = bookRequestService;
    }

    @PostMapping
    public ResponseEntity<?> insertBookRequest(@RequestBody @Valid BookRequestDTO bookRequestDTO) {
        try {
            BookRequest bookRequest = BookRequestConverter.convertToEntity(bookRequestDTO);
            BookRequest insertedBookRequest = bookRequestService.insertBookRequest(bookRequest);
            BookRequestDTO bookRequestDTOReturn = BookRequestConverter.convertToBookReqWithIdDTO(insertedBookRequest);
            return new ResponseEntity<>(bookRequestDTOReturn, HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(bookRequestService.findAll()
                .stream()
                .map(BookRequestConverter::convertToBookReqWithIdDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateBookRequest(@RequestBody @Valid BookReqUpdateDTO bookReqUpdateDTO) {
        try {
            BookRequestStatus.valueOf(bookReqUpdateDTO.getBookReqStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("status invalid! Accepted status: PENDING,ACCEPTED,REJECTED",
                    HttpStatus.BAD_REQUEST);
        }
        try {
            BookRequest bookRequest = BookRequestConverter.convertToEntity(bookReqUpdateDTO);
            BookRequest updatedBookRequest = bookRequestService.updateBookRequest(bookRequest);
            return new ResponseEntity<>(BookRequestConverter.convertToBookReqWithIdDTO(updatedBookRequest),
                    HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBookRequest(@RequestParam int bookReqId) {
        return new ResponseEntity<>(bookRequestService.deleteBookRequest(bookReqId), HttpStatus.OK);
    }
}
