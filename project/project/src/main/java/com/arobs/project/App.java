package com.arobs.project;

import com.arobs.project.entity.Employee;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {
        Employee employee = new Employee("name1", "pass", "email@yahoo.com", "user");
        Transaction transaction = null;
    }
}

