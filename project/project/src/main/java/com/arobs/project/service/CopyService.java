package com.arobs.project.service;

import com.arobs.project.entity.Copy;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface CopyService {


    Copy insertCopy(Copy copy) throws ValidationException;

    boolean deleteCopy(int copyId);

    List<Copy> findAll();

    List<Copy> findCopiesForBookByStatus(int bookId, CopyStatus copyStatus) throws ValidationException;

    Copy updateCopy(Copy copy) throws ValidationException;

}
