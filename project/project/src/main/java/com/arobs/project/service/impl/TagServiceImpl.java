package com.arobs.project.service.impl;

import com.arobs.project.converter.TagConverter;
import com.arobs.project.dto.TagWithIdDTO;
import com.arobs.project.entity.Tag;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.repository.TagRepository;
import com.arobs.project.service.TagService;
import com.arobs.project.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public TagWithIdDTO insertTag(String description) {
        return TagConverter.convertToTagWithIdDTO(tagRepository.insertTag(new Tag(description)));
    }


    public Tag findByDescription(String description) throws ValidationException {
        Tag tag = ValidationService.safeGetUniqueResult(tagRepository.findByDescription(description));
        if (tag != null) {
            return tag;
        } else {
            throw new ValidationException("tag does not exist");
        }

    }

}
