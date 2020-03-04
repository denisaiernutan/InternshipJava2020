package com.arobs.project.converter;

import com.arobs.project.dto.tag.TagWithIdDTO;
import com.arobs.project.entity.Tag;
import org.modelmapper.ModelMapper;

public class TagConverter {

    private static ModelMapper modelMapper = new ModelMapper();

    public static TagWithIdDTO convertToDTO(Tag tag) {
        return modelMapper.map(tag, TagWithIdDTO.class);
    }

    public static Tag convertToEntity(TagWithIdDTO tagDTO) {
        return modelMapper.map(tagDTO, Tag.class);
    }

    public static TagWithIdDTO convertToTagWithIdDTO(Tag tag) {
        return modelMapper.map(tag, TagWithIdDTO.class);
    }


}
