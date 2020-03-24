package com.arobs.project.service.impl;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookTag;
import com.arobs.project.entity.Copy;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Book insertBook(Book book) {
        book.setBookAddedDate(new Timestamp(System.currentTimeMillis()));
        Set<Tag> tagSet = listTags(book.getTagSet());
        //strategy ?
        if (bookRepository.getClass().getName().contains("BookJDBCRepository")) {
            book = insertBookJDBC(book, tagSet);
        } else {
            book = insertBookHibernate(book, tagSet);
        }
        return book;
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

    private Set<Tag> listTags(Set<Tag> tagSet) {
        Set<Tag> tagEntitySet = new HashSet<>(15);
        if (tagSet != null && !tagSet.isEmpty()) {
            for (Tag tagDTO : tagSet) {
                Tag tag;
                try {
                    tag = tagService.findByDescription(tagDTO.getTagDescription());
                } catch (ValidationException e) {
                    tag = tagService.insertTag(tagDTO.getTagDescription());
                }
                tagEntitySet.add(tag);
            }
        }
        return tagEntitySet;
    }

    @Transactional
    public Book updateBook(Book book) throws ValidationException {
        Book foundBook = bookRepository.findById(book.getBookId());
        if (foundBook == null) {
            throw new ValidationException("invalid id");
        }
        Set<Tag> tagSet = listTags(book.getTagSet());
        foundBook.setTagSet(tagSet);
        foundBook.setBookDescription(book.getBookDescription());
        foundBook.setBookAuthor(book.getBookAuthor());
        foundBook.setBookTitle(book.getBookTitle());

        return foundBook;
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
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book findById(int bookId) {
        return bookRepository.findById(bookId);
    }


    @Transactional
    public List<Copy> findCopies(int bookId) throws ValidationException {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new ValidationException("book id invalid");
        }
        return new ArrayList<>(book.getCopySet());
    }

}
