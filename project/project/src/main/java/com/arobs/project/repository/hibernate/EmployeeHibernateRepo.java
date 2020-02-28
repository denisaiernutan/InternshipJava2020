package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Employee;
import com.arobs.project.repository.EmployeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeHibernateRepo implements EmployeeRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public EmployeeHibernateRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Employee", Employee.class).list();
    }

    @Override
    public Employee insertEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update Employee set employeePass= MD5(:password) where employeeEmail= :email";
        int id = (int) session.save(employee);
        session.createQuery(hql).setParameter("password", employee.getEmployeePass()).setParameter("email", employee.getEmployeeEmail()).executeUpdate();
        employee.setEmployeeId(id);
        return employee;
    }

    @Override
    public String getPasswordById(int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, employeeId);
        if (employee != null) {
            return employee.getEmployeePass();
        } else return null;
    }

    @Override
    public Employee updatePassword(int employeeId, String employeePass) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update Employee set employeePass= MD5(:password) where employeeId= :id";
        session.createQuery(hql).setParameter("password", employeePass).setParameter("id", employeeId).executeUpdate();
        return findById(employeeId);
    }

    @Override
    public List<Employee> findByEmail(String employeeEmail) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Employee where employeeEmail= :employeeEmail";
        return (List<Employee>) session.createQuery(hql).setParameter("employeeEmail", employeeEmail).list();

    }

    @Override
    public boolean deleteById(int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete from Employee where employeeId= :id";
        session.createQuery(hql).setParameter("id", employeeId).executeUpdate();
        return true;
    }

    public Employee findById(int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, employeeId);
    }
}
