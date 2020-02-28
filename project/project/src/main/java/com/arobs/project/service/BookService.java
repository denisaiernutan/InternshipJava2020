package com.arobs.project.service;

import com.arobs.project.dto.BookDTO;
import com.arobs.project.exception.ValidationException;

public interface BookService {

     BookDTO insertBook(BookDTO bookDTO) throws ValidationException;
}
