package com.arobs.project.controller;

import com.arobs.project.dto.CopyDTO;
import com.arobs.project.dto.CopyUpdateDTO;
import com.arobs.project.dto.CopyWithIdDTO;
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
        CopyWithIdDTO copyWithIdDTO = copyService.insertCopy(copyDTO);
        if (copyWithIdDTO != null) {
            return new ResponseEntity<>(copyWithIdDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("book id invalid or status invalid : AVAILABLE, RENTED, PENDING", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCopy(@RequestParam int copyId) {
        return new ResponseEntity<>(copyService.deleteCopy(copyId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCopy(@RequestBody CopyUpdateDTO copyUpdateDTO) {
        CopyWithIdDTO copyWithIdDTO = copyService.updateCopy(copyUpdateDTO);
        if (copyWithIdDTO != null) {
            return new ResponseEntity<>(copyWithIdDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("id invalid ", HttpStatus.BAD_REQUEST);
        }
    }
}
