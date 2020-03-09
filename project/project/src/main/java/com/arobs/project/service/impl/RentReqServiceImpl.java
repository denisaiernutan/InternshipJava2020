package com.arobs.project.service.impl;
import com.arobs.project.repository.RentRequestRepository;
import com.arobs.project.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentReqServiceImpl implements RentRequestService {

    private RentRequestRepository rentRequestRepository;

    @Autowired
    public RentReqServiceImpl(RentRequestRepository rentRequestRepository) {
        this.rentRequestRepository = rentRequestRepository;
    }
}
