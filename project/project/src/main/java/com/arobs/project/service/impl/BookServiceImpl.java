package com.arobs.project.service.impl;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.converter.TagConverter;
import com.arobs.project.dto.BookDTO;
import com.arobs.project.dto.TagDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookTag;
import com.arobs.project.entity.Tag;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.BookRepository;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.service.BookService;
import com.arobs.project.service.BookTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

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
        if (!bookDTO.getTagSet().isEmpty()) {
            if (bookRepository.getClass().getName().contains("BookJDBCRepository")) {
                book = insertBookJDBC(book, bookDTO.getTagSet());
            } else {
                book = insertBookHibernate(book, bookDTO.getTagSet());
            }
        } else {
            book = bookRepository.insertBook(book);
        }

        return BookConverter.convertToDTO(book);
    }

    @Transactional
    public Book insertBookJDBC(Book book, Set<TagDTO> tagDTOSet) {
        book = bookRepository.insertBook(book);
        for (TagDTO tagDTO : tagDTOSet) {
            Tag tag;
            try {
                tag = tagService.findByDescription(tagDTO.getTagDescription());
            } catch (ValidationException e) {
                tag = TagConverter.convertToEntity(tagService.insertTag(tagDTO.getTagDescription()));
            }
            bookTagService.insertBookTag(new BookTag(book, tag));
        }
        return book;
    }


    @Transactional
    public Book insertBookHibernate(Book book, Set<TagDTO> tagDTOSet)  {

        Set<Tag> tagSet = new HashSet<>(15);
        for (TagDTO tagDTO : tagDTOSet) {
            Tag tag;
            try {
                tag = tagService.findByDescription(tagDTO.getTagDescription());
            } catch (ValidationException e) {
                tag = TagConverter.convertToEntity(tagService.insertTag(tagDTO.getTagDescription()));
            }
            tagSet.add(tag);
        }
        book.setTagSet(tagSet);

        book = bookRepository.insertBook(book);
        return book;
    }
}
