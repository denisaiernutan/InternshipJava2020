package com.arobs.project;

import com.arobs.project.entity.Employee;
import com.arobs.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class App {

    public static void main(String[] args) {
        Employee employee = new Employee("name1", "pass", "email@yahoo.com", "user");
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(employee);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List< Employee > employees = session.createQuery("from Employee", Employee.class).list();
            employees.forEach( e -> System.out.println(e.getEmployeeEmail()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}

