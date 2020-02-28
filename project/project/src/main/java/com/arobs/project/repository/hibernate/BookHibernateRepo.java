package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Book;
import com.arobs.project.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookHibernateRepo implements BookRepository {


    private SessionFactory sessionFactory;

    @Autowired
    public BookHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book insertBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        int id = (int) session.save(book);
        book.setBookId(id);
        return book;
    }
}
