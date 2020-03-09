package com.arobs.project.controller;

import com.arobs.project.converter.CopyConverter;
import com.arobs.project.dto.copy.CopyDTO;
import com.arobs.project.dto.copy.CopyUpdateDTO;
import com.arobs.project.dto.copy.CopyWithIdDTO;
import com.arobs.project.entity.Copy;
import com.arobs.project.exception.ValidationException;
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

    @Autowired
    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(copyService.findAll().stream().map(CopyConverter::convertToCopyWithIdDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> insertCopy(@RequestBody CopyDTO copyDTO) {
        try {
            return new ResponseEntity<>(CopyConverter.convertToCopyWithIdDTO(copyService.insertCopy(CopyConverter.convertToEntity(copyDTO))), HttpStatus.OK);
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
            Copy updatedCopy = copyService.updateCopy(CopyConverter.convertToEntity(copyUpdateDTO));
            CopyWithIdDTO copyWithIdDTO = CopyConverter.convertToCopyWithIdDTO(updatedCopy);
            return new ResponseEntity<>(copyWithIdDTO, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
