package com.arobs.project.service;

import com.arobs.project.entity.BookRent;
import com.arobs.project.entity.RentRequest;
import com.arobs.project.exception.ValidationException;

public interface BookRentManager {


    RentRequest confirmRentRequest(int rentRequestId) throws ValidationException;

    BookRent returnBook(BookRent bookRent) throws ValidationException;

    RentRequest declineRentRequest(int rentRequestId) throws ValidationException;
}
