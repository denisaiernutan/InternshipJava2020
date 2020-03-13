package com.arobs.project.service;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Tag;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.repository.BookRepository;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.repository.hibernate.HibernateRepoFactory;
import com.arobs.project.service.impl.BookServiceImpl;
import com.arobs.project.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Mock
    TagServiceImpl tagService;


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

    @Test
    void whenListTags_givenTagSet_returnSetTagsFromDb() throws ValidationException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Set<Tag> tagSet = new HashSet<>(5);
        tagSet.add(new Tag(1, "tag1"));
        tagSet.add(new Tag(2, "tag2"));
        tagSet.add(new Tag(3, "tag3"));

        when(tagService.findByDescription(any(String.class))).thenReturn(new Tag());
        Method method = bookService.getClass().getDeclaredMethod("listTags", Set.class);
        method.setAccessible(true);
        Set<Tag> tags = (Set<Tag>) method.invoke(bookService, tagSet);
        assertFalse(tags.isEmpty());
    }


}
