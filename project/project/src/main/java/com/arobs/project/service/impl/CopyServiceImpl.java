package com.arobs.project.service.impl;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Copy;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.CopyRepository;
import com.arobs.project.service.BookService;
import com.arobs.project.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CopyServiceImpl implements CopyService {

    CopyRepository copyRepository;

    BookService bookService;

    @Autowired
    public CopyServiceImpl(CopyRepository copyRepository, BookService bookService) {
        this.copyRepository = copyRepository;
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public Copy insertCopy(Copy copy) throws ValidationException {
        if (copy.getCopyStatus().toUpperCase().equals(CopyStatus.AVAILABLE.toString())) {
            Book book = bookService.findById(copy.getBook().getBookId());
            if (book == null) {
                throw new ValidationException("book id invalid");
            }
            copy.setBook(book);
            return copyRepository.insertCopy(copy);
        } else {
            throw new ValidationException("status invalid! accepted status: AVAILABLE");
        }

    }
    @Override
    @Transactional
    public boolean deleteCopy(int copyId) {
        Copy copy = copyRepository.findById(copyId);
        if (copy != null) {
            return copyRepository.deleteCopy(copy);
        }
        return false;
    }

    @Override
    @Transactional
    public List<Copy> findAll() {
        return copyRepository.findAll();
    }

    @Transactional
    @Override
    public List<Copy> findAvailableCopiesForBook(int bookId) {
        if (bookService.findById(bookId) != null) {
            return copyRepository.findAvailableCopiesForBook(bookId);
        }
        return null;
    }

    @Transactional
    @Override
    public Copy updateCopy(Copy copy) throws ValidationException {
        try {
            CopyStatus.valueOf(copy.getCopyStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("invalid status. Accepted status: AVAILABLE, RENTED,PENDING");
        }
        Copy updateCopy = copyRepository.findById(copy.getCopyId());
        if (updateCopy == null) {
            throw new ValidationException("invalid copy id");
        }
        return copyRepository.updateCopy(copy);

    }
}
