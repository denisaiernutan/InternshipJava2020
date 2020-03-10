package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.BookRequest;
import com.arobs.project.repository.BookRequestRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookReqHibernateRepo implements BookRequestRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public BookReqHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BookRequest insertBookRequest(BookRequest bookRequest) {
        Session session = sessionFactory.getCurrentSession();
        int id = (int) session.save(bookRequest);
        bookRequest.setBookReqId(id);
        return bookRequest;
    }

    @Override
    public List<BookRequest> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("Select b from BookRequest as b join fetch b.employee", BookRequest.class).list();
    }

    @Override
    public BookRequest updateBookRequest(BookRequest bookRequest) {
        Session session = sessionFactory.getCurrentSession();
        BookRequest updatedBookReq = session.get(BookRequest.class, bookRequest.getBookReqId());
        updatedBookReq.setBookReqStatus(bookRequest.getBookReqStatus());
        return updatedBookReq;
    }

    @Override
    public boolean deleteBookRequest(BookRequest bookRequest) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(bookRequest);
        return true;
    }

    @Override
    public BookRequest findById(int bookReqId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BookRequest.class, bookReqId);
    }
}
