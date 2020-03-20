package com.arobs.project.service.impl;

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
    public Copy insertCopy(Copy copy) {
        return copyRepository.insertCopy(copy);
    }

    @Override
    @Transactional
    public boolean deleteCopy(int copyId) {
        Copy copy = copyRepository.findById(copyId);
        if (copy != null) {
            copy.setCopyFlag(false);
            return true;
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
    public List<Copy> findCopiesForBookByStatus(int bookId, CopyStatus copyStatus) throws ValidationException {
        List<Copy> copyList = copyRepository.findRentableCopiesForBookByStatus(bookId, copyStatus);
        if (copyList == null) {
            throw new ValidationException("book id invalid");
        }
        return copyList;
    }

    @Transactional
    @Override
    public Copy updateCopy(Copy copy) throws ValidationException {
        Copy updateCopy = copyRepository.findById(copy.getCopyId());
        copy.setCopyStatus(copy.getCopyStatus().toUpperCase());
        if (updateCopy == null) {
            throw new ValidationException("invalid copy id");
        }
        return copyRepository.updateCopy(copy);
    }
}
