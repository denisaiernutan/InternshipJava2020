package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.RentRequest;
import com.arobs.project.repository.RentRequestRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
