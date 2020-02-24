package com.arobs.project.service.impl;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.converter.TagConverter;
import com.arobs.project.dto.BookDTO;
import com.arobs.project.dto.TagDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookTag;
import com.arobs.project.entity.Tag;
import com.arobs.project.repository.BookRepository;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.service.BookService;
import com.arobs.project.service.BookTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;

@Service
public class BookServiceImpl implements BookService {

    private RepoFactory repoFactory;

    private BookRepository bookRepository;

    private TagServiceImpl tagService;

    private BookTagService bookTagService;

    @Autowired
    public BookServiceImpl(RepoFactory repoFactory, TagServiceImpl tagService, BookTagService bookTagService) {
        this.repoFactory = repoFactory;
        this.tagService = tagService;
        this.bookTagService = bookTagService;
    }

    @PostConstruct
    public void init() {
        this.bookRepository = this.repoFactory.getInstance().getBookRepository();
    }

    @Transactional
    public BookDTO insertBook(BookDTO bookDTO) {
        Book book = BookConverter.convertToEntity(bookDTO);
        book.setBookAddedDate(new Timestamp(System.currentTimeMillis()));
        book = bookRepository.insertBook(book);

        if (bookDTO.getTagList().size() != 0) {
            for (TagDTO tagDTO : bookDTO.getTagList()) {
                Tag tag = tagService.findByDescription(tagDTO.getTagDescription());
                if (tag == null) {
                    tag = TagConverter.convertToEntity(tagService.insertTag(tagDTO.getTagDescription()));
                }
                bookTagService.insertBookTag(new BookTag(book, tag));
            }
        }
        return BookConverter.convertToDTO(book);
    }

}
