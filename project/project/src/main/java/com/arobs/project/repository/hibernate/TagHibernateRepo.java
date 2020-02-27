package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Employee;
import com.arobs.project.entity.Tag;
import com.arobs.project.repository.TagRepository;
import com.arobs.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagHibernateRepo implements TagRepository {

    @Override
    public List<Tag> findByDescription(String description) {
        String hql = "from Tag where tagDescription= :descr";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Tag> tags = (List<Tag>) session.createQuery(hql).setParameter("descr", description).list();
            transaction.commit();
            return tags;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Tag insertTag(Tag tag) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int id = (int) session.save(tag);
            transaction.commit();
            tag.setTagId(id);
            return tag;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }



}
