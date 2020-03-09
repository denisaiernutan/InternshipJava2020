package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Copy;
import com.arobs.project.repository.CopyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CopyHibernateRepo implements CopyRepository {

    SessionFactory sessionFactory;

    @Autowired
    public CopyHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Copy> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT DISTINCT c from Copy c join fetch c.book as b join fetch b.tagSet ", Copy.class).list();

    }

    @Override
    public Copy insertCopy(Copy copy) {
        Session session = sessionFactory.getCurrentSession();
        int id = (int) session.save(copy);
        copy.setCopyId(id);
        return copy;
    }

    @Override
    public Copy updateCopy(Copy copy) {
        Session session = sessionFactory.getCurrentSession();
        Copy updatedCopy = session.createQuery("SELECT DISTINCT c from Copy c join fetch c.book as b join fetch b.tagSet where c.copyId= :copyId",
                Copy.class).setParameter("copyId", copy.getCopyId()).getSingleResult();
        updatedCopy.setCopyFlag(copy.isCopyFlag());
        updatedCopy.setCopyStatus(copy.getCopyStatus());
        return updatedCopy;
    }

    @Override
    public boolean deleteCopy(Copy copy) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(copy);
        return true;
    }

    @Override
    public Copy findById(int copyId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Copy.class, copyId);
    }

    @Override
    public List<Copy> findAvailableCopiesForBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT c from Copy c INNER JOIN c.book b where b.bookId= :bookid and c.copyStatus='available' and c.copyFlag=true";
        return session.createQuery(hql, Copy.class).setParameter("bookid", bookId).list();
    }


}
