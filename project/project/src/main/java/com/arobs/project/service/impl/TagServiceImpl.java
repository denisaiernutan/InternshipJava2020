package com.arobs.project.service.impl;

import com.arobs.project.converter.TagConverter;
import com.arobs.project.dto.TagDTO;
import com.arobs.project.entity.Tag;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.repository.TagRepository;
import com.arobs.project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TagServiceImpl implements TagService {


    private RepoFactory repoFactory;

    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(RepoFactory repoFactory) {
        this.repoFactory = repoFactory;
    }

    @PostConstruct
    public void init() {
        this.tagRepository = this.repoFactory.getInstance().getTagRepository();
    }

    public TagDTO insertTag(String description) {
        int size = description.length();
        return TagConverter.convertToDTO(tagRepository.insertTag(description.substring(1, size - 1)));
    }

    public Tag findByDescription(String description) {
        return tagRepository.findByDescription(description);
    }

}
