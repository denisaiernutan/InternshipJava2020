package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Employee;
import com.arobs.project.repository.EmployeeRepository;
import com.arobs.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeHibernateRepo implements EmployeeRepository {


    @Override
    public List<Employee> findAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
            transaction.commit();
            return employees;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        Transaction transaction = null;
        String hql = "update Employee set employeePass= MD5(:password) where employeeEmail= :email";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            session.createQuery(hql).setParameter("password", employee.getEmployeePass()).setParameter("email", employee.getEmployeeEmail()).executeUpdate();
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPasswordByEmail(String employeeEmail) {
        String hql = "from Employee where employeeEmail= :employeeEmail";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.createQuery(hql).setParameter("employeeEmail", employeeEmail).getSingleResult();
            transaction.commit();
            return employee.getEmployeePass();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee updatePassword(String employeeEmail, String employeePass) {
        String hql = "update Employee set employeePass= MD5(:password) where employeeEmail= :email";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(hql).setParameter("password", employeePass).setParameter("email", employeeEmail).executeUpdate();
            transaction.commit();
            return new Employee(employeeEmail);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<Employee> findByEmail(String employeeEmail) {
        String hql = "from Employee where employeeEmail= :employeeEmail";
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Employee> listEmployee = (List<Employee>) session.createQuery(hql).setParameter("employeeEmail", employeeEmail).list();
            transaction.commit();
            return listEmployee;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteByEmail(String employeeEmail) {
        String hql = "delete from Employee where employeeEmail= :email";

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(hql).setParameter("email", employeeEmail).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
