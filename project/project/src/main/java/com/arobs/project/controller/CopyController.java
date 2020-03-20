package com.arobs.project.controller;

import com.arobs.project.converter.CopyConverter;
import com.arobs.project.dto.copy.CopyDTO;
import com.arobs.project.dto.copy.CopyUpdateDTO;
import com.arobs.project.dto.copy.CopyWithIdDTO;
import com.arobs.project.entity.Copy;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentManager;
import com.arobs.project.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/copies")
public class CopyController {

    private CopyService copyService;

    private BookRentManager bookRentManager;

    @Autowired
    public CopyController(CopyService copyService, BookRentManager bookRentManager) {
        this.copyService = copyService;
        this.bookRentManager = bookRentManager;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(copyService.findAll()
                .stream()
                .map(CopyConverter::convertToCopyWithIdDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertAvailableCopy(@RequestBody CopyDTO copyDTO) {
        try {
            Copy newCopy = CopyConverter.convertToEntity(copyDTO);
            Copy insertedCopy = bookRentManager.insertAvailableCopy(newCopy);
            return new ResponseEntity<>(CopyConverter.convertToCopyWithIdDTO(insertedCopy), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCopy(@RequestParam int copyId) {
        return new ResponseEntity<>(copyService.deleteCopy(copyId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCopy(@RequestBody CopyUpdateDTO copyUpdateDTO) {
        try {
            CopyStatus.valueOf(copyUpdateDTO.getCopyStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("invalid status. Accepted status: AVAILABLE, RENTED,PENDING",
                    HttpStatus.BAD_REQUEST);
        }
        try {
            Copy updatedCopy = copyService.updateCopy(CopyConverter.convertToEntity(copyUpdateDTO));
            CopyWithIdDTO copyWithIdDTO = CopyConverter.convertToCopyWithIdDTO(updatedCopy);
            return new ResponseEntity<>(copyWithIdDTO, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
