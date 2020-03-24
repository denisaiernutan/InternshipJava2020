package com.arobs.project.repository;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Copy;

import java.util.List;
import java.util.Set;

public interface BookRepository {

    Book insertBook(Book book);

    List<Book> findAll();

    Book updateBook(Book book);

    boolean deleteBook(Book book);

    Book findById(int bookId);


    Set<Copy> findCopies(int bookId);

}
