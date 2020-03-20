package com.arobs.project.service;

import com.arobs.project.entity.BookRent;
import com.arobs.project.entity.Copy;
import com.arobs.project.entity.RentRequest;
import com.arobs.project.exception.ValidationException;

public interface BookRentManager {


    BookRent returnBook(BookRent bookRent) throws ValidationException;

    Copy insertAvailableCopy(Copy copy) throws ValidationException;

    RentRequest acceptRentRequest(boolean accepted, int rentRequestId) throws ValidationException;
}
