package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.RentRequest;
import com.arobs.project.enums.RentReqStatus;
import com.arobs.project.repository.RentRequestRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RentReqHibernateRepo implements RentRequestRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public RentReqHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public RentRequest insertRentRequest(RentRequest rentRequest) {
        Session session = sessionFactory.getCurrentSession();
        int id = (int) session.save(rentRequest);
        rentRequest.setRentReqId(id);
        return rentRequest;
    }

    @Override
    public List<RentRequest> findByBookByStatus(int bookId, RentReqStatus rentReqStatus) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from RentRequest rr join fetch rr.employee as e " +
                "join fetch rr.book as b " +
                "where b.bookId= :bookId and " +
                "rr.rentReqStatus= :rentReqStatus ";
        return session.createQuery(hql, RentRequest.class)
                .setParameter("rentReqStatus", rentReqStatus.toString())
                .setParameter("bookId", bookId).list();
    }

    @Override
    public RentRequest findById(int rentRequestId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(RentRequest.class, rentRequestId);
    }

    @Override
    public List<RentRequest> findWaitForConfirmationEarlierThan(Date date) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from RentRequest rr join fetch rr.employee as e " +
                "join fetch rr.book as b " +
                "where rr.rentReqStatus= 'WAITING_FOR_CONFIRMATION' " +
                "and rr.requestDate<= :date";
        return session.createQuery(hql, RentRequest.class).setParameter("date", date).list();
    }
}
