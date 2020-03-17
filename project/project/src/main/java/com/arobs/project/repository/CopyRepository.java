package com.arobs.project.repository;

import com.arobs.project.entity.Copy;
import com.arobs.project.enums.CopyStatus;

import java.util.List;

public interface CopyRepository {

    List<Copy> findAll();

    Copy insertCopy(Copy copy);

    Copy updateCopy(Copy copy);

    boolean deleteCopy(Copy copy);

    Copy findById(int copyId);

    List<Copy> findCopiesForBookByStatus(int bookId, CopyStatus copyStatus);

}
