package com.arobs.project.service;


import com.arobs.project.entity.RentRequest;
import com.arobs.project.exception.ValidationException;

public interface RentRequestService {

    RentRequest insertRentRequest(int bookId, int employeeId) throws ValidationException;
}
