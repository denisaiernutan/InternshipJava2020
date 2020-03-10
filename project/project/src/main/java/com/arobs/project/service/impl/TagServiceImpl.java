package com.arobs.project.service.impl;

import com.arobs.project.converter.BookConverter;
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
import java.util.stream.Collectors;

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
        if (tag != null) {
            return tag;
        } else {
            throw new ValidationException("tag does not exist");
        }
    }

    @Transactional
    public boolean deleteTag(int tagId) {
        Tag tag = tagRepository.findById(tagId);
        if (tag != null) {
            return tagRepository.deleteTag(tag);
        } else {
            return false;
        }
    }

    @Transactional
    public List<Book> findBooks(int tagId) {
        Tag tag = tagRepository.findById(tagId);
        if (tag != null) {
            return new ArrayList<>(tagRepository.findBooks(tagId));
        } else {
            return null;
        }
    }
}
