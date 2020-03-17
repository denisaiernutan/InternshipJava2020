package com.arobs.project.service.impl;

import com.arobs.project.entity.RentRequest;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentManager;
import com.arobs.project.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class SchedulerService {

    private BookRentManager bookRentManager;
    private RentRequestService rentRequestService;

    @Autowired
    public SchedulerService(BookRentManager bookRentManager, RentRequestService rentRequestService) {
        this.bookRentManager = bookRentManager;
        this.rentRequestService = rentRequestService;
    }


    //1 hour
    @Scheduled(fixedRate = 60000 * 60)
    @Async
    public void waitForConfirmationRentRequestLessThan24Hours() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Date date = calendar.getTime();

        List<RentRequest> rentRequestList = rentRequestService.findRentReqWaitForConfirmationEarlierThan(date);

        for (RentRequest rentRequest : rentRequestList) {
            try {
                bookRentManager.declineRentRequest(rentRequest.getRentReqId());
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }


    }
}
