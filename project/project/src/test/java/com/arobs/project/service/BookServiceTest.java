package com.arobs.project.service;

import com.arobs.project.entity.Book;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.BookRepository;
import com.arobs.project.repository.hibernate.BookHibernateRepo;
import com.arobs.project.repository.hibernate.HibernateRepoFactory;
import com.arobs.project.service.impl.BookServiceImpl;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    HibernateRepoFactory hibernateRepoFactory;

    //not working
    @Test
    void whenInsertBook_givenBook_returnBook() {
        bookRepository = hibernateRepoFactory.getBookRepository();
        Book bookToInsert = new Book(0, "bookTitle", "bookAuthor", "bookDescription");
        Book insertedBook = bookToInsert;
        insertedBook.setBookId(20);
        when(bookRepository.insertBook(bookToInsert)).thenReturn(insertedBook);
        Book book = bookService.insertBook(bookToInsert);
        assertEquals(book, insertedBook);
    }


}
