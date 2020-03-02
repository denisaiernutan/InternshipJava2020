package com.arobs.project.service;

import com.arobs.project.dto.TagWithIdDTO;
import com.arobs.project.entity.Tag;
import com.arobs.project.exception.ValidationException;

public interface TagService {

    TagWithIdDTO insertTag(String description);

    Tag findByDescription(String description) throws ValidationException;

    boolean deleteTag(TagWithIdDTO tagDTO);
}
