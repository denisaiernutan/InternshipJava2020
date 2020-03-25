package com.arobs.project.service;

import com.arobs.project.entity.*;
import com.arobs.project.exception.ValidationException;

public interface BookRentService {

    BookRent tryToMakeBookRent(int bookId, int employeeId) throws ValidationException;

    BookRent findById(int bookRentId);

    BookRent askForExtensionOfRental(int bookRentId) throws ValidationException;

    void markBookRentAsLate();

    BookRent returnBook(BookRent bookRent) throws ValidationException;

    RentRequest acceptRentRequest(boolean accepted, int rentRequestId) throws ValidationException;

    Copy insertAvailableCopy(int bookId) throws ValidationException;
}
