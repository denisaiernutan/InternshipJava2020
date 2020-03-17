package com.arobs.project.repository;

import com.arobs.project.entity.RentRequest;
import com.arobs.project.enums.RentReqStatus;

import java.util.Date;
import java.util.List;

public interface RentRequestRepository {

    RentRequest insertRentRequest(RentRequest rentRequest);

   List<RentRequest> findByBookByStatus(int bookId, RentReqStatus rentReqStatus);

    RentRequest findById(int rentRequestId);

    List<RentRequest> findWaitForConfirmationEarlierThan(Date date);
}
