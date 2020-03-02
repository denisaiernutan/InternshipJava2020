package com.arobs.project.repository;

import com.arobs.project.entity.Book;

import java.util.List;

public interface BookRepository {

    Book insertBook(Book book);

    List<Book> findAll();

    Book updateBook(Book book);

    boolean deleteBook(Book book);

    Book findById(int bookId);
}
