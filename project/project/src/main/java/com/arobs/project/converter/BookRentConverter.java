package com.arobs.project.converter;

import com.arobs.project.dto.bookRent.BookRentInsertDTO;
import com.arobs.project.entity.BookRent;
import org.modelmapper.ModelMapper;

public class BookRentConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static BookRent convertToEntity(BookRentInsertDTO bookRentInsertDTO) {
        return modelMapper.map(bookRentInsertDTO, BookRent.class);
    }

    public static BookRentInsertDTO convertToBookRentInsertDTO(BookRent bookRent) {
        return modelMapper.map(bookRent, BookRentInsertDTO.class);
    }
}
