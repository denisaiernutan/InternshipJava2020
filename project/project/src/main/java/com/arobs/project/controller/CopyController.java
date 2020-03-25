package com.arobs.project.controller;

import com.arobs.project.converter.CopyConverter;
import com.arobs.project.dto.copy.CopyUpdateDTO;
import com.arobs.project.dto.copy.CopyWithIdDTO;
import com.arobs.project.entity.Copy;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentService;
import com.arobs.project.service.CopyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/copies")
public class CopyController {

    private CopyService copyService;
    private BookRentService bookRentService;
    private static final Logger logger = LoggerFactory.getLogger("FILE");

    @Autowired
    public CopyController(CopyService copyService, BookRentService bookRentService) {
        this.copyService = copyService;
        this.bookRentService = bookRentService;

    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(copyService.findAll()
                .stream()
                .map(CopyConverter::convertToCopyWithIdDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertAvailableCopy(@RequestParam int bookId) {
        try {
            Copy insertedCopy = bookRentService.insertAvailableCopy(bookId);
            return new ResponseEntity<>(CopyConverter.convertToCopyWithIdDTO(insertedCopy), HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCopy(@RequestParam int copyId) {
        return new ResponseEntity<>(copyService.deleteCopy(copyId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCopy(@RequestBody @Valid CopyUpdateDTO copyUpdateDTO) {
        try {
            CopyStatus.valueOf(copyUpdateDTO.getCopyStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("invalid status. Accepted status: AVAILABLE, RENTED,PENDING",
                    HttpStatus.BAD_REQUEST);
        }
        try {
            Copy updatedCopy = copyService.updateCopy(CopyConverter.convertToEntity(copyUpdateDTO));
            CopyWithIdDTO copyWithIdDTO = CopyConverter.convertToCopyWithIdDTO(updatedCopy);
            return new ResponseEntity<>(copyWithIdDTO, HttpStatus.OK);
        } catch (ValidationException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
