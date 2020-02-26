package com.arobs.project.repository.hibernate;

import com.arobs.project.repository.BookRepository;
import com.arobs.project.repository.EmployeeRepository;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("hibernateRepoFactory")
public class HibernateRepoFactory extends RepoFactory {

    @Autowired
    private EmployeeHibernateRepo employeeHibernateRepo;

    @Override
    public EmployeeRepository getEmployeeRepository() {
        return employeeHibernateRepo;
    }

    @Override
    public BookRepository getBookRepository() {
        return null;
    }

    @Override
    public TagRepository getTagRepository() {
        return null;
    }


}
