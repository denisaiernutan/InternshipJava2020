package com.arobs.project.repository.jdbc;

import com.arobs.project.repository.RepoFactory;
import com.arobs.project.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class JDBCRepoFactory extends RepoFactory {

    @Autowired
    private EmployeeJDBCRepository employeeJDBCRepository;

    @Autowired
    private BookJDBCRepository bookJDBCRepository;

    @Autowired
    private TagJDBCRepository tagJDBCRepository;

    @Autowired
    private BookTagJDBCRepository bookTagJDBCRepository;

    public EmployeeJDBCRepository getEmployeeRepository() {
        return employeeJDBCRepository;
    }

    public BookJDBCRepository getBookRepository() {
        return bookJDBCRepository;
    }


    public TagRepository getTagRepository() {
        return tagJDBCRepository;
    }

    public BookTagJDBCRepository getBookTagJDBCRepository() {
        return bookTagJDBCRepository;
    }


}
