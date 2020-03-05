package com.arobs.project.service;

import com.arobs.project.dto.copy.CopyDTO;
import com.arobs.project.dto.copy.CopyUpdateDTO;
import com.arobs.project.dto.copy.CopyWithIdDTO;
import com.arobs.project.entity.Copy;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface CopyService {


    CopyWithIdDTO insertCopy(CopyDTO copyDTO) throws ValidationException;

    CopyWithIdDTO updateCopy(CopyUpdateDTO copyDTO) throws ValidationException;

    boolean deleteCopy(int copyId);

    List<CopyWithIdDTO> findAll();

    List<Copy> findAvailableCopiesForBook(int bookId);

}
