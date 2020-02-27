package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Book;
import com.arobs.project.repository.BookRepository;
import com.arobs.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class BookHibernateRepo implements BookRepository {
    @Override
    public Book insertBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int id= (int) session.save(book);
            transaction.commit();
            book.setBookId(id);
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
