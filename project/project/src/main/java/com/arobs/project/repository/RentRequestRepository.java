package com.arobs.project.repository;

import com.arobs.project.entity.RentRequest;

import java.util.List;

public interface RentRequestRepository {

    RentRequest insertRentRequest(RentRequest rentRequest);

    List<RentRequest> findByBook(int bookId);
}
