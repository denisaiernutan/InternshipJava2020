package com.arobs.project.controller;

import com.arobs.project.dto.copy.CopyDTO;
import com.arobs.project.dto.copy.CopyUpdateDTO;
import com.arobs.project.dto.copy.CopyWithIdDTO;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/copies")
public class CopyController {

    private CopyService copyService;

    @Autowired
    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(copyService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertCopy(@RequestBody CopyDTO copyDTO) {
        try {
            CopyWithIdDTO copyWithIdDTO = copyService.insertCopy(copyDTO);
            return new ResponseEntity<>(copyWithIdDTO, HttpStatus.OK);
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
            CopyWithIdDTO copyWithIdDTO = copyService.updateCopy(copyUpdateDTO);
            return new ResponseEntity<>(copyWithIdDTO, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
