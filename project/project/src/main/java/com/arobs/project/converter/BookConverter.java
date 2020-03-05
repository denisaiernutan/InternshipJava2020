package com.arobs.project.converter;

import com.arobs.project.dto.book.BookDTO;
import com.arobs.project.dto.book.BookUpdateDTO;
import com.arobs.project.dto.book.BookWithIdDTO;
import com.arobs.project.entity.Book;
import org.modelmapper.ModelMapper;

public class BookConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public static BookDTO convertToDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public static Book convertToEntity(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    public static BookWithIdDTO convertToBookWithIdDTO(Book book) {
        return modelMapper.map(book, BookWithIdDTO.class);
    }

    public static Book convertToEntity(BookWithIdDTO bookWithIdDTO){
        return modelMapper.map(bookWithIdDTO, Book.class);
    }

    public static Book convertToEntity(BookUpdateDTO bookWithIdDTO){
        return modelMapper.map(bookWithIdDTO, Book.class);
    }
}
