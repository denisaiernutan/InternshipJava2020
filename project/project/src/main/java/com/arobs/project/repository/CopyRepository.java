package com.arobs.project.repository;

import com.arobs.project.entity.Copy;

import java.util.List;

public interface CopyRepository {

    List<Copy> findAll();

    Copy insertCopy(Copy copy);

    Copy updateCopy(Copy copy);

    boolean deleteCopy(Copy copy);

    Copy findById(int copyId);
}
