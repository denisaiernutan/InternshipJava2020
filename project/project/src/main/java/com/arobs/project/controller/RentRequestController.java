package com.arobs.project.controller;

import com.arobs.project.converter.RentRequestConverter;
import com.arobs.project.dto.bookRent.BookRentInsertDTO;
import com.arobs.project.entity.RentRequest;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentManager;
import com.arobs.project.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rentrequests")
public class RentRequestController {

    private RentRequestService rentRequestService;

    private BookRentManager bookRentManager;

    @Autowired
    public RentRequestController(RentRequestService rentRequestService, BookRentManager bookRentManager) {
        this.rentRequestService = rentRequestService;
        this.bookRentManager = bookRentManager;
    }

    @PostMapping
    public ResponseEntity<?> insertRentRequest(@RequestBody BookRentInsertDTO bookRentInsertDTO) {
        try {
            RentRequest rentRequest = rentRequestService.insertRentRequest(bookRentInsertDTO.getBook(),
                    bookRentInsertDTO.getEmployee());
            return new ResponseEntity<>(RentRequestConverter.convertToRentReqWithIdDTO(rentRequest), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/decline")
    public ResponseEntity<?> declineRentRequest(@RequestParam int rentRequestId) {
        try {
            RentRequest rentRequest = bookRentManager.declineRentRequest(rentRequestId);
            return new ResponseEntity<>(RentRequestConverter.convertToRentReqWithIdDTO(rentRequest), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/confirm")
    public ResponseEntity<?> confirmRentRequest(@RequestParam int rentRequestId) {
        try {
            RentRequest rentRequest = bookRentManager.confirmRentRequest(rentRequestId);
            return new ResponseEntity<>(RentRequestConverter.convertToRentReqWithIdDTO(rentRequest), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
