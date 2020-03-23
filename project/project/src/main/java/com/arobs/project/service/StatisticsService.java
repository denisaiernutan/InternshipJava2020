package com.arobs.project.service;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Employee;

import java.sql.Date;
import java.util.List;

public interface StatisticsService {

    List<Book> listXBooksRented(int noOfBooks, Date startDate, Date endDate);

    List<Employee> listXEmployeesByNoOfBooksRead(int noOfEmployees, Date startDate, Date endDate);

}
