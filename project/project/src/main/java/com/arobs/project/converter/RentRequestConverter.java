package com.arobs.project.converter;

import com.arobs.project.dto.rentRequest.RentRequestWithIdDTO;
import com.arobs.project.entity.RentRequest;
import org.modelmapper.ModelMapper;

public class RentRequestConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static RentRequestWithIdDTO convertToRentReqWithIdDTO(RentRequest rentRequest) {
        RentRequestWithIdDTO rentRequestDTO = new RentRequestWithIdDTO(rentRequest.getRentReqId(),
                rentRequest.getRequestDate(), rentRequest.getRentReqStatus());
        rentRequestDTO.setEmployee(rentRequest.getEmployee().getEmployeeId());
        rentRequestDTO.setBook(rentRequest.getBook().getBookId());
        return rentRequestDTO;
    }
}
