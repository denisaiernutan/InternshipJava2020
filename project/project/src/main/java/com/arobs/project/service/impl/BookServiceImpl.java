package com.arobs.project.service.impl;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.converter.CopyConverter;
import com.arobs.project.converter.TagConverter;
import com.arobs.project.dto.book.BookDTO;
import com.arobs.project.dto.book.BookWithIdDTO;
import com.arobs.project.dto.copy.CopyUpdateDTO;
import com.arobs.project.dto.tag.TagDTO;
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
import java.util.HashSet;
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
        Set<Tag> tagSet = listTags(bookDTO.getTagSet());

        //strategy ?
        if (bookRepository.getClass().getName().contains("BookJDBCRepository")) {
            book = insertBookJDBC(book, tagSet);
        } else {
            book = insertBookHibernate(book, tagSet);
        }
        return BookConverter.convertToDTO(book);
    }

    @Transactional
    public Book insertBookJDBC(Book book, Set<Tag> tagSet) {
        book = bookRepository.insertBook(book);
        if (!tagSet.isEmpty()) {
            for (Tag tag : tagSet) {
                bookTagService.insertBookTag(new BookTag(book, tag));
            }
        }
        return book;
    }

    @Transactional
    public Book insertBookHibernate(Book book, Set<Tag> tagSet) {
        book.setTagSet(tagSet);
        book = bookRepository.insertBook(book);
        return book;
    }

    public Set<Tag> listTags(Set<TagDTO> tagDTOSet) {
        Set<Tag> tagSet = new HashSet<>(15);
        if (tagDTOSet != null && !tagDTOSet.isEmpty()) {
            for (TagDTO tagDTO : tagDTOSet) {
                Tag tag;
                try {
                    tag = tagService.findByDescription(tagDTO.getTagDescription());
                } catch (ValidationException e) {
                    tag = TagConverter.convertToEntity(tagService.insertTag(tagDTO.getTagDescription()));
                }
                tagSet.add(tag);
            }
        }
        return tagSet;
    }

    @Transactional
    public BookWithIdDTO updateBook(BookWithIdDTO bookDTO) {
        if (bookRepository.findById(bookDTO.getBookId()) != null) {
            return BookConverter.convertToBookWithIdDTO(bookRepository.updateBook(BookConverter.convertToEntity(bookDTO)));
        } else return null;
    }


    @Transactional
    public boolean deleteBook(int bookId) {
        Book book = bookRepository.findById(bookId);
        if (book != null) {
            return bookRepository.deleteBook(book);
        }
        return false;
    }


    @Transactional
    public List<BookWithIdDTO> findAll() {
        return bookRepository.findAll().stream().map(BookConverter::convertToBookWithIdDTO).collect(Collectors.toList());
    }

    @Transactional
    public Book findById(int bookId) {
        return bookRepository.findById(bookId);
    }


    @Transactional
    public List<CopyUpdateDTO> findCopies(int bookId) {
        if (bookRepository.findById(bookId) != null) {
            return bookRepository.findCopies(bookId).stream().map(CopyConverter::convertToCopyUpdateDTO).collect(Collectors.toList());
        }
        return null;
    }
}
