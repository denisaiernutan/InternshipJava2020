package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.BookRent;
import com.arobs.project.repository.BookRentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
        String hql = "from BookRent br join fetch br.employee e where br.bookRentId= :bookRentId";
        return session.createQuery(hql, BookRent.class).setParameter("bookRentId", bookRentId).list().get(0);
    }

    @Override
    public List<BookRent> findBookRentThatPassedReturnDate() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from BookRent br where br.returnDate< :returnDate and br.bookRentStatus='ON_GOING'";
        return session.createQuery(hql, BookRent.class).setParameter("returnDate", new Date()).list();
    }
}
