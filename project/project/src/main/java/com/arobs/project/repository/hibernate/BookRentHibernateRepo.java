package com.arobs.project.repository.hibernate;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.BookRent;
import com.arobs.project.entity.Employee;
import com.arobs.project.repository.BookRentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Book> listXBooksRented(int noOfBooks, java.sql.Date startDate, java.sql.Date endDate) {
        Session session = sessionFactory.getCurrentSession();

        List<Object[]> objectList = session.createQuery(buildHqlSelectBooks(), Object[].class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setMaxResults(noOfBooks)
                .getResultList();

        return getBooksRented(objectList);
    }

    @Override
    public List<Book> listBooksRented(java.sql.Date startDate, java.sql.Date endDate) {
        Session session = sessionFactory.getCurrentSession();

        List<Object[]> objectList = session.createQuery(buildHqlSelectBooks(), Object[].class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();

        return getBooksRented(objectList);

    }

    private String buildHqlSelectBooks() {
        return "select distinct b, count(b) from BookRent br " +
                "inner join Book b on b.bookId=br.book.bookId join fetch b.tagSet" +
                " where br.rentalDate >= :startDate and br.rentalDate <= :endDate" +
                " group by b " +
                "order by count(b) desc";
    }

    private List<Book> getBooksRented(List<Object[]> objectList) {
        return objectList.stream().map(o -> (Book) o[0]).collect(Collectors.toList());

    }

    @Override
    public List<Employee> listXEmployeesByNoOfBooksRead(int noOfEmployees, java.sql.Date startDate, java.sql.Date endDate) {
        Session session = sessionFactory.getCurrentSession();
        String selectEmployees = " select distinct e , count(e) from BookRent br " +
                "inner join Employee e on e.employeeId= br.employee.employeeId" +
                " where br.returnDate BETWEEN :startDate and :endDate and br.bookRentStatus= 'RETURNED'" +
                " group by e " +
                "order by count(e) desc";

        List<Object[]> objectList = session.createQuery(selectEmployees, Object[].class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setMaxResults(noOfEmployees)
                .getResultList();

        return objectList.stream()
                .map(o -> (Employee) o[0])
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> listLateEmployees() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select distinct employee from BookRent br where br.bookRentStatus='LATE'";
        return session.createQuery(hql, Employee.class).list();
    }
}
