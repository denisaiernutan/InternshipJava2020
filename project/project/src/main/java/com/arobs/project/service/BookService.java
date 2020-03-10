package com.arobs.project.service;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Copy;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface BookService {

    Book insertBook(Book book) throws ValidationException;

    List<Book> findAll();

    Book updateBook(Book book) throws ValidationException;

    boolean deleteBook(int bookId);

    Book findById(int bookId);

    List<Copy> findCopies(int bookId) throws ValidationException;

    boolean existBookInDb(int bookId);
}
