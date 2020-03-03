package com.arobs.project.converter;

import com.arobs.project.dto.BookRequestDTO;
import com.arobs.project.entity.BookRequest;
import org.modelmapper.ModelMapper;

public class BookRequestConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public BookRequest convertToEntity(BookRequestDTO bookRequestDTO) {
        return modelMapper.map(bookRequestDTO, BookRequest.class);
    }

    public BookRequestDTO convertToDTO(BookRequest bookRequest) {
        return modelMapper.map(bookRequest, BookRequestDTO.class);
    }
}
