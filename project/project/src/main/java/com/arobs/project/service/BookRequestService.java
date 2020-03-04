package com.arobs.project.service;

import com.arobs.project.dto.bookRequest.BookReqUpdateDTO;
import com.arobs.project.dto.bookRequest.BookReqWithIdDTO;
import com.arobs.project.dto.bookRequest.BookRequestDTO;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface BookRequestService {

    BookRequestDTO insertBookRequest(BookRequestDTO bookRequestDTO) throws ValidationException;

    BookRequestDTO updateBookRequest(BookReqUpdateDTO bookReqUpdateDTO) throws ValidationException;

    boolean deleteBookRequest(int bookReqId);

    List<BookReqWithIdDTO> findAll();

}
