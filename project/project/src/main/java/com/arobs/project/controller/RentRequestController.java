package com.arobs.project.controller;

import com.arobs.project.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentRequestController {

    private RentRequestService rentRequestService;

    @Autowired
    public RentRequestController(RentRequestService rentRequestService) {
        this.rentRequestService = rentRequestService;
    }
}
