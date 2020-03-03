package com.arobs.project.service.impl;

import com.arobs.project.converter.CopyConverter;
import com.arobs.project.dto.CopyDTO;
import com.arobs.project.dto.CopyUpdateDTO;
import com.arobs.project.dto.CopyWithIdDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.entity.Copy;
import com.arobs.project.enums.CopyStatus;
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
    public CopyWithIdDTO insertCopy(CopyDTO copyDTO) {
        try {
            CopyStatus.valueOf(copyDTO.getCopyStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
        Book book = bookService.findById(copyDTO.getBook().getBookId());

        if (book != null) {
            Copy copy = CopyConverter.convertToEntity(copyDTO);
            copy.setBook(book);
            return CopyConverter.convertToCopyWithIdDTO(copyRepository.insertCopy(copy));
        }
        return null;
    }

    @Override
    @Transactional
    public CopyWithIdDTO updateCopy(CopyUpdateDTO copyDTO) {
        Copy copy = copyRepository.findById(copyDTO.getCopyId());
        if (copy != null) {
            return CopyConverter.convertToCopyWithIdDTO(copyRepository.updateCopy(CopyConverter.convertToEntity(copyDTO)));
        }
        return null;

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
