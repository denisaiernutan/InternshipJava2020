package com.arobs.project.service;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Tag;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface TagService {

    Tag insertTag(String description);

    Tag findByDescription(String description) throws ValidationException;

    boolean deleteTag(int tagId);

    List<Book> findBooks(int tagId);
}
