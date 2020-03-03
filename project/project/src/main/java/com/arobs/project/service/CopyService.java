package com.arobs.project.service;

import com.arobs.project.dto.CopyDTO;
import com.arobs.project.dto.CopyUpdateDTO;
import com.arobs.project.dto.CopyWithIdDTO;

import java.util.List;

public interface CopyService {


    CopyWithIdDTO insertCopy(CopyDTO copyDTO);

    CopyWithIdDTO updateCopy(CopyUpdateDTO copyDTO);

    boolean deleteCopy(int copyId);

    List<CopyWithIdDTO> findAll();
}
