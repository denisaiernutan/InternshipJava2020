package com.arobs.project.service;

import com.arobs.project.entity.Book;
import com.arobs.project.repository.BookRepository;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.repository.hibernate.HibernateRepoFactory;
import com.arobs.project.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    HibernateRepoFactory hibernateRepoFactory;

    @Mock
    RepoFactory repoFactory;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(repoFactory.getInstance()).thenReturn(hibernateRepoFactory);
        when(repoFactory.getInstance().getBookRepository()).thenReturn(bookRepository);
        this.bookService.init();
    }

    @Test
    void whenInsertBook_givenBook_returnBook() {
        Book bookToInsert = new Book(0, "bookTitle", "bookAuthor", "bookDescription");
        Book insertedBook = bookToInsert;
        insertedBook.setBookId(20);
        when(bookRepository.insertBook(any(Book.class))).thenReturn(insertedBook);
        Book book = bookService.insertBook(bookToInsert);
        assertEquals(book, insertedBook);
    }


}
