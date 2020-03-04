package com.arobs.project.service;

import com.arobs.project.dto.book.BookDTO;
import com.arobs.project.dto.tag.TagWithIdDTO;
import com.arobs.project.entity.Tag;
import com.arobs.project.exception.ValidationException;

import java.util.List;

public interface TagService {

    TagWithIdDTO insertTag(String description);

    Tag findByDescription(String description) throws ValidationException;

    boolean deleteTag(int tagId);

    List<BookDTO> findBooks(int tagId);
}
