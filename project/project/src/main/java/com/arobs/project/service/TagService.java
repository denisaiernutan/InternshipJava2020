package com.arobs.project.service;

import com.arobs.project.dto.TagDTO;
import com.arobs.project.entity.Tag;

public interface TagService {

    TagDTO insertTag(String description);

    Tag findByDescription(String description);
}
