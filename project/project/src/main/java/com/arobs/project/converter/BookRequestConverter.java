package com.arobs.project.converter;

import com.arobs.project.dto.bookRequest.BookReqUpdateDTO;
import com.arobs.project.dto.bookRequest.BookReqWithIdDTO;
import com.arobs.project.dto.bookRequest.BookRequestDTO;
import com.arobs.project.entity.BookRequest;
import org.modelmapper.ModelMapper;

public class BookRequestConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public static BookRequest convertToEntity(BookRequestDTO bookRequestDTO) {
        return modelMapper.map(bookRequestDTO, BookRequest.class);
    }

    public static BookRequestDTO convertToDTO(BookRequest bookRequest) {
        BookRequestDTO bookRequestDTO = modelMapper.map(bookRequest, BookRequestDTO.class);
        bookRequestDTO.setEmployeeId(bookRequest.getEmployee().getEmployeeId());
        return bookRequestDTO;
    }

    public static BookReqWithIdDTO convertToBookReqWithIdDTO(BookRequest bookRequest) {
        BookReqWithIdDTO bookReqWithIdDTO = modelMapper.map(bookRequest, BookReqWithIdDTO.class);
        bookReqWithIdDTO.setEmployeeId(bookRequest.getEmployee().getEmployeeId());
        return bookReqWithIdDTO;
    }

    public static BookRequest convertToEntity(BookReqUpdateDTO bookRequestDTO) {
        return modelMapper.map(bookRequestDTO, BookRequest.class);
    }

}
