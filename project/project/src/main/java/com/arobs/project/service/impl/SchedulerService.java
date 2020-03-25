package com.arobs.project.service.impl;

import com.arobs.project.entity.RentRequest;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookRentService;
import com.arobs.project.service.EmployeeService;
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

    private RentRequestService rentRequestService;
    private BookRentService bookRentService;
    private EmployeeService employeeService;

    @Autowired
    public SchedulerService(RentRequestService rentRequestService,
                            BookRentService bookRentService, EmployeeService employeeService) {
        this.rentRequestService = rentRequestService;
        this.bookRentService = bookRentService;
        this.employeeService = employeeService;
    }


    //1 hour
    @Scheduled(fixedRate = 60000 * 60)
    @Async
    public void waitForConfirmationRentRequestLessThan24Hours() {
        int WAIT_FOR_CONFIRMATION_TIME = 24;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -WAIT_FOR_CONFIRMATION_TIME);
        Date date = calendar.getTime();

        List<RentRequest> rentRequestList = rentRequestService.findRentReqWaitForConfirmationEarlierThan(date);

        for (RentRequest rentRequest : rentRequestList) {
            try {
                bookRentService.acceptRentRequest(false, rentRequest.getRentReqId());
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }
    }

    //daily
    @Scheduled(fixedRate = 24 * 60000 * 60)
    @Async
    public void markBookRentAsLate() {
        bookRentService.markBookRentAsLate();
    }

    @Scheduled(fixedRate = 24 * 60000 * 60)
    @Async
    public void releaseBanningFromEmployees() {
        employeeService.releaseBanningForEmployees();
    }

}
