package com.arobs.project.repository;

import com.arobs.project.repository.hibernate.HibernateRepoFactory;
import com.arobs.project.repository.jdbc.JDBCRepoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public abstract class RepoFactory {

    @Value("${datasource.connection.type}")
    private String datasourceConnectionType;

    @Autowired
    private JDBCRepoFactory jdbcRepoFactory;

    @Autowired
    private HibernateRepoFactory hibernateRepoFactory;


    public RepoFactory getInstance() {
        if (datasourceConnectionType.equals("JDBCTemplate")) {
            return jdbcRepoFactory;
        } else {
            return hibernateRepoFactory;
        }
    }

    public abstract EmployeeRepository getEmployeeRepository();

    public abstract BookRepository getBookRepository();

    public abstract TagRepository getTagRepository();

    public BookTagRepository getBookTagRepository() {
        return jdbcRepoFactory.getBookTagJDBCRepository();
    }

}
