package com.arobs.project.controller;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.converter.EmployeeConverter;
import com.arobs.project.service.StatisticsService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {

    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/topxbooks")
    public ResponseEntity<?> listXBookRented(@ApiParam(defaultValue = "0") @RequestParam int noOfBooks,
                                             @RequestParam Date startDate, @RequestParam Date endDate) {
        return new ResponseEntity<>(statisticsService.listXBooksRented(noOfBooks, startDate, endDate)
                .stream()
                .map(BookConverter::convertToBookWithIdDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/topxemployees")
    public ResponseEntity<?> listXEmployeesByNoOfBooksRead(@RequestParam int noOfEmployees,
                                                           @RequestParam Date startDate, @RequestParam Date endDate) {
        return new ResponseEntity<>(statisticsService.listXEmployeesByNoOfBooksRead(noOfEmployees, startDate, endDate)
                .stream()
                .map(EmployeeConverter::convertToEmployeeDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/listlateemployees")
    public ResponseEntity<?> listLateEmployees() {
        return new ResponseEntity<>(statisticsService.listLateEmployees()
                .stream()
                .map(EmployeeConverter::convertToEmployeeDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
