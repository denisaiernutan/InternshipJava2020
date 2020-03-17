package com.arobs.project.service;


import com.arobs.project.entity.RentRequest;
import com.arobs.project.enums.RentReqStatus;
import com.arobs.project.exception.ValidationException;

import java.util.Date;
import java.util.List;

public interface RentRequestService {

    RentRequest insertRentRequest(int bookId, int employeeId) throws ValidationException;

    List<RentRequest> findByBookByStatus(int bookId, RentReqStatus rentReqStatus);

    RentRequest findById(int rentRequestId);

    List<RentRequest> findRentReqWaitForConfirmationEarlierThan(Date date);

}
