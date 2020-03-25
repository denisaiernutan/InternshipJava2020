package com.arobs.project.controller;

import com.arobs.project.converter.RentRequestConverter;
import com.arobs.project.entity.RentRequest;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentService;
import com.arobs.project.service.RentRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rentrequests")
public class RentRequestController {

    private RentRequestService rentRequestService;
    private BookRentService bookRentService;
    private static final Logger logger = LoggerFactory.getLogger("FILE");

    @Autowired
    public RentRequestController(RentRequestService rentRequestService, BookRentService bookRentService) {
        this.rentRequestService = rentRequestService;
        this.bookRentService = bookRentService;
    }

    @PostMapping
    public ResponseEntity<?> insertRentRequest(@RequestParam int bookId, @RequestParam int employeeId) {
        try {
            RentRequest rentRequest = rentRequestService.insertRentRequest(bookId, employeeId);
            return new ResponseEntity<>(RentRequestConverter.convertToRentReqWithIdDTO(rentRequest), HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> acceptRentRequest(@RequestParam boolean accepted, @RequestParam int rentRequestId) {
        try {
            RentRequest rentRequest = bookRentService.acceptRentRequest(accepted, rentRequestId);
            return new ResponseEntity<>(RentRequestConverter.convertToRentReqWithIdDTO(rentRequest), HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
