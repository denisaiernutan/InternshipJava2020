package com.arobs.project.service.impl;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.converter.TagConverter;
import com.arobs.project.dto.BookDTO;
import com.arobs.project.dto.BookWithIdDTO;
import com.arobs.project.dto.TagWithIdDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookTag;
import com.arobs.project.entity.Tag;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.BookRepository;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.service.BookService;
import com.arobs.project.service.BookTagService;
import com.arobs.project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private RepoFactory repoFactory;

    private BookRepository bookRepository;

    private TagService tagService;

    private BookTagService bookTagService;

    @Autowired
    public BookServiceImpl(RepoFactory repoFactory, TagService tagService, BookTagService bookTagService) {
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

        if (bookRepository.getClass().getName().contains("BookJDBCRepository") && !bookDTO.getTagSet().isEmpty()) {
            book = insertBookJDBC(book, bookDTO.getTagSet());
        } else {
            book = bookRepository.insertBook(book);
        }

        return BookConverter.convertToDTO(book);
    }

    @Transactional
    public Book insertBookJDBC(Book book, Set<TagWithIdDTO> tagDTOSet) {
        book = bookRepository.insertBook(book);
        for (TagWithIdDTO tagDTO : tagDTOSet) {
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
    public BookWithIdDTO updateBook(BookWithIdDTO bookDTO) {
        if (bookRepository.findById(bookDTO.getBookId()) != null) {
            return BookConverter.convertToBookWithIdDTO(bookRepository.updateBook(BookConverter.convertToEntity(bookDTO)));
        } else return null;
    }


    @Transactional
    public boolean deleteBook(BookWithIdDTO bookDTO) {
        Book book=bookRepository.findById(bookDTO.getBookId());
        if (book!= null) {
            return bookRepository.deleteBook(book);
        } else return false;
    }
//    @Transactional
//    public Book insertBookHibernate(Book book, Set<TagWithIdDTO> tagDTOSet)  {
//
//        Set<Tag> tagSet = new HashSet<>(15);
//        for (TagWithIdDTO tagDTO : tagDTOSet) {
//            Tag tag;
//            try {
//                tag = tagService.findByDescription(tagDTO.getTagDescription());
//            } catch (ValidationException e) {
//                tag = TagConverter.convertToEntity(tagService.insertTag(tagDTO.getTagDescription()));
//            }
//            tagSet.add(tag);
//        }
//        book.setTagSet(tagSet);
//
//        book = bookRepository.insertBook(book);
//        return book;
//    }

    @Transactional
    public List<BookWithIdDTO> findAll() {
        return bookRepository.findAll().stream().map(BookConverter::convertToBookWithIdDTO).collect(Collectors.toList());
    }
}
