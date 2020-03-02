package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Book;
import com.arobs.project.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Book> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Book", Book.class).list();
    }

    public Book updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        Book updateBook = session.get(Book.class, book.getBookId());
        updateBook.setTagSet(book.getTagSet());
        updateBook.setBookDescription(book.getBookDescription());
        return updateBook;
    }

    public boolean deleteBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(book);
        return true;
    }

    public Book findById(int bookId){
        Session session= this.sessionFactory.getCurrentSession();
        return session.get(Book.class, bookId);
    }
}
