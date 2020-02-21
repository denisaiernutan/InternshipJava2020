package com.arobs.project.service;

import com.arobs.project.converter.TagConverter;
import com.arobs.project.dto.TagDTO;
import com.arobs.project.entity.Tag;
import com.arobs.project.repository.TagJDBCRepository;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {


    private TagJDBCRepository tagJDBCRepository;

    public TagServiceImpl(TagJDBCRepository tagJDBCRepository) {
        this.tagJDBCRepository = tagJDBCRepository;
    }

    public TagDTO insertTag(String description) {
        return TagConverter.convertToDTO(tagJDBCRepository.insertTag(description));
    }

    public Tag findByDescription(String description){
        return tagJDBCRepository.findByDescription(description);
    }

}
