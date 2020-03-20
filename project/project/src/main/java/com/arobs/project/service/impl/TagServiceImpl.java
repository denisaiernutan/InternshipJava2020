package com.arobs.project.service.impl;

import com.arobs.project.entity.Book;
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
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public Tag insertTag(String description) {
        return tagRepository.insertTag(new Tag(description));
    }


    @Transactional
    public Tag findByDescription(String description) throws ValidationException {
        Tag tag = ValidationService.safeGetUniqueResult(tagRepository.findByDescription(description));
        if (tag == null) {
            throw new ValidationException("tag does not exist");
        }
        return tag;
    }

    @Transactional
    public boolean deleteTag(int tagId) {
        Tag tag = tagRepository.findById(tagId);
        if (tag == null) {
            return false;
        }
        return tagRepository.deleteTag(tag);

    }

    @Transactional
    public List<Book> findBooks(int tagId) throws ValidationException {
        Tag tag = tagRepository.findById(tagId);
        if (tag == null) {
            throw new ValidationException("invalid id");
        }
        return new ArrayList<>(tagRepository.findBooks(tagId));

    }

}
