package com.arobs.project.service.impl;

import com.arobs.project.converter.CopyConverter;
import com.arobs.project.dto.copy.CopyDTO;
import com.arobs.project.dto.copy.CopyUpdateDTO;
import com.arobs.project.dto.copy.CopyWithIdDTO;
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
import java.util.stream.Collectors;

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
    public CopyWithIdDTO insertCopy(CopyDTO copyDTO) throws ValidationException {
        if (copyDTO.getCopyStatus().toUpperCase().equals(CopyStatus.AVAILABLE.toString())) {
            Book book = bookService.findById(copyDTO.getBook().getBookId());
            if (book == null) {
                throw new ValidationException("book id invalid");
            }
            Copy copy = CopyConverter.convertToEntity(copyDTO);
            copy.setBook(book);
            return CopyConverter.convertToCopyWithIdDTO(copyRepository.insertCopy(copy));
        } else {
            throw new ValidationException("status invalid! accepted status: AVAILABLE");
        }

    }

    @Override
    @Transactional
    public CopyWithIdDTO updateCopy(CopyUpdateDTO copyDTO) throws ValidationException {

        try {
            CopyStatus.valueOf(copyDTO.getCopyStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("invalid status. Accepted status: AVAILABLE, RENTED,PENDING");
        }
        Copy copy = copyRepository.findById(copyDTO.getCopyId());
        if (copy == null) {
            throw new ValidationException("invalid copy id");
        }
        return CopyConverter.convertToCopyWithIdDTO(copyRepository.updateCopy(CopyConverter.convertToEntity(copyDTO)));


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
    public List<CopyWithIdDTO> findAll() {
        return copyRepository.findAll().stream().map(CopyConverter::convertToCopyWithIdDTO).collect(Collectors.toList());

    }
}
