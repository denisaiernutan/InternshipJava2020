package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Tag;
import com.arobs.project.repository.TagRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class TagHibernateRepo implements TagRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public TagHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Tag> findByDescription(String description) {
        String hql = "from Tag where tagDescription= :descr";
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(hql, Tag.class)
                .setParameter("descr", description)
                .list();
    }

    @Override
    public Tag insertTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        int id = (int) session.save(tag);
        tag.setTagId(id);
        return tag;
    }

    public boolean deleteTag(Tag tag) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(tag);
        return true;
    }

    public Tag findById(int tagId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Tag.class, tagId);
    }

    @Override
    public Set<Book> findBooks(int tagId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Tag.class, tagId)
                .getBookSet();
    }
}
