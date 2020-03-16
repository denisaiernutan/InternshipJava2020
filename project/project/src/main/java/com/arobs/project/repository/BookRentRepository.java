package com.arobs.project.repository;

import com.arobs.project.entity.BookRent;

import java.util.List;

public interface BookRentRepository {

    BookRent insertBookRent(BookRent bookRent);

    List<BookRent> findById(int bookRentId);
}
