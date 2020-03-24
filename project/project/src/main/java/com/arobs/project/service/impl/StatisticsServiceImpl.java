package com.arobs.project.service.impl;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Employee;
import com.arobs.project.repository.BookRentRepository;
import com.arobs.project.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private BookRentRepository bookRentRepository;

    @Autowired
    public StatisticsServiceImpl(BookRentRepository bookRentRepository) {
        this.bookRentRepository = bookRentRepository;
    }

    @Override
    @Transactional
    public List<Book> listXBooksRented(int noOfBooks, Date startDate, Date endDate) {
        if (noOfBooks == 0) {
            return bookRentRepository.listBooksRented(startDate, endDate);
        }
        return bookRentRepository.listXBooksRented(noOfBooks, startDate, endDate);
    }

    @Override
    @Transactional
    public List<Employee> listXEmployeesByNoOfBooksRead(int noOfEmployees, Date startDate, Date endDate) {
        return bookRentRepository.listXEmployeesByNoOfBooksRead(noOfEmployees, startDate, endDate);
    }

    @Override
    @Transactional
    public List<Employee> listLateEmployees() {
        return bookRentRepository.listLateEmployees();
    }
}
