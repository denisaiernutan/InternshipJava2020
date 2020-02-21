package com.arobs.project.converter;

import com.arobs.project.dto.TagDTO;
import com.arobs.project.entity.Tag;
import org.modelmapper.ModelMapper;

public class TagConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static TagDTO convertToDTO(Tag tag) {
        return modelMapper.map(tag, TagDTO.class);
    }

    public static Tag convertToEntity(TagDTO tagDTO){
        return modelMapper.map(tagDTO,Tag.class);
    }

}
