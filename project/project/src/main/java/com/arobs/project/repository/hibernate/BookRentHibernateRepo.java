package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.BookRent;
import com.arobs.project.repository.BookRentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRentHibernateRepo implements BookRentRepository {


    SessionFactory sessionFactory;

    @Autowired
    public BookRentHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BookRent insertBookRent(BookRent bookRent) {
        Session session = sessionFactory.getCurrentSession();
        int id = (int) session.save(bookRent);
        bookRent.setBookRentId(id);
        return bookRent;
    }

    @Override
    public BookRent findById(int bookRentId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BookRent.class, bookRentId);
//        String hql = "from BookRent br join fetch br.book as b join fetch br.copy as c where br.bookRentId= :bookRentId";
//        return session.createQuery(hql, BookRent.class).setParameter("bookRentId", bookRentId).list();
    }
}
