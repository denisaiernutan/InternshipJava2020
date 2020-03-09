package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Copy;
import com.arobs.project.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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

    @Override
    public List<Book> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("SELECT DISTINCT b from Book as b JOIN FETCH b.tagSet", Book.class).list();
    }

    @Override
    public Book updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        Book updateBook = session.get(Book.class, book.getBookId());
        updateBook.setTagSet(book.getTagSet());
        updateBook.setBookDescription(book.getBookDescription());
        return updateBook;
    }

    @Override
    public boolean deleteBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(book);
        return true;
    }

    @Override
    public Book findById(int bookId) {
        Session session = this.sessionFactory.getCurrentSession();
        String hql = "SELECT b from Book b join fetch b.tagSet where b.bookId= :bookid";
        //fix it
        return session.createQuery(hql, Book.class).setParameter("bookid", bookId).getSingleResult();

    }

    @Override
    public boolean existBookInDb(int bookId) {
        Session session = this.sessionFactory.getCurrentSession();
        return null != session.get(Book.class, bookId);
    }

    @Override
    public Set<Copy> findCopies(int bookId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Book.class, bookId).getCopySet();
    }
}
