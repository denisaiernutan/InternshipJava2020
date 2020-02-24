package com.arobs.project.service.impl;

import com.arobs.project.entity.BookTag;
import com.arobs.project.repository.BookTagRepository;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.service.BookTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BookTagServiceImpl implements BookTagService {


    private RepoFactory repoFactory;

    private BookTagRepository bookTagRepository;

    @Autowired
    public BookTagServiceImpl(RepoFactory repoFactory) {
        this.repoFactory = repoFactory;
    }

    @PostConstruct
    public void init() {
        this.bookTagRepository = this.repoFactory.getInstance().getBookTagRepository();
    }

    public BookTag insertBookTag(BookTag bookTag) {
        return bookTagRepository.insertBookTag(bookTag);
    }
}
