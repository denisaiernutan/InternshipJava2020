package com.arobs.project.controller;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.dto.book.BookDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void whenInsertBook_givenBookDTO_returnResponseEntity() throws ValidationException {
        BookDTO bookDTO = new BookDTO("titleBook", "bookAuthor", "bookDescription", new HashSet<>(2));
        Book insertedBook = BookConverter.convertToEntity(bookDTO);
        insertedBook.setBookId(20);
        when(bookService.insertBook(any(Book.class))).thenReturn(insertedBook);
        ResponseEntity<?> responseEntity = bookController.insertBook(bookDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
