package com.arobs.project.converter;

import com.arobs.project.dto.copy.CopyDTO;
import com.arobs.project.dto.copy.CopyUpdateDTO;
import com.arobs.project.dto.copy.CopyWithIdDTO;
import com.arobs.project.entity.Copy;
import org.modelmapper.ModelMapper;

public class CopyConverter {
    private static ModelMapper modelMapper = new ModelMapper();

    public static Copy convertToEntity(CopyDTO copyDTO) {
        return modelMapper.map(copyDTO, Copy.class);
    }

    public static CopyDTO convertToDTO(Copy copy) {
        return modelMapper.map(copy, CopyDTO.class);
    }

    public static Copy convertToEntity(CopyWithIdDTO copyDTO) {
        return modelMapper.map(copyDTO, Copy.class);
    }

    public static CopyWithIdDTO convertToCopyWithIdDTO(Copy copy) {
        return modelMapper.map(copy, CopyWithIdDTO.class);
    }

    public static Copy convertToEntity(CopyUpdateDTO copyUpdateDTO) {
        return modelMapper.map(copyUpdateDTO, Copy.class);
    }

    public static CopyUpdateDTO convertToCopyUpdateDTO(Copy copy) {
        return modelMapper.map(copy, CopyUpdateDTO.class);
    }

}
