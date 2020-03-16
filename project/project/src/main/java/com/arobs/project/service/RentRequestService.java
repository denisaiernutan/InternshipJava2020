package com.arobs.project.service;


import com.arobs.project.entity.RentRequest;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface RentRequestService {

    RentRequest insertRentRequest(int bookId, int employeeId) throws ValidationException;

    List<RentRequest> findByBook(int bookId);
}
