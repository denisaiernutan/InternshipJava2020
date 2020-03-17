package com.arobs.project.repository;

import com.arobs.project.entity.BookRent;

import java.util.List;

public interface BookRentRepository {

    BookRent insertBookRent(BookRent bookRent);

    BookRent findById(int bookRentId);
}
