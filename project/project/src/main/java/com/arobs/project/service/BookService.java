package com.arobs.project.service;

import com.arobs.project.dto.BookDTO;
import com.arobs.project.dto.BookWithIdDTO;
import com.arobs.project.dto.CopyUpdateDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface BookService {

    BookDTO insertBook(BookDTO bookDTO) throws ValidationException;

    List<BookWithIdDTO> findAll();

    BookWithIdDTO updateBook(BookWithIdDTO bookWithIdDTO);

    boolean deleteBook(int bookId);

    Book findById(int bookId);

    List<CopyUpdateDTO> findCopies(int bookId);
}
