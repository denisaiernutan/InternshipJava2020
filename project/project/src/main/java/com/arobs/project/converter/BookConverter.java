package com.arobs.project.converter;

import com.arobs.project.dto.BookDTO;
import com.arobs.project.entity.Book;
import org.modelmapper.ModelMapper;

public class BookConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public static BookDTO convertToDTO(Book book){
        return modelMapper.map(book,BookDTO.class);
    }

    public static Book convertToEntity(BookDTO bookDTO){
        return  modelMapper.map(bookDTO,Book.class);
    }
}
