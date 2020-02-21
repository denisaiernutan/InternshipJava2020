package com.arobs.project.service;

import com.arobs.project.entity.BookTag;
import com.arobs.project.repository.BookTagJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookTagServiceImpl implements  BookTagService {

    private BookTagJDBCRepository bookTagJDBCRepository;

    @Autowired
    public BookTagServiceImpl(BookTagJDBCRepository bookTagJDBCRepository) {
        this.bookTagJDBCRepository = bookTagJDBCRepository;
    }

    public BookTag insertBookTag(BookTag bookTag) {
       return  bookTagJDBCRepository.insertBookTag(bookTag);
    }
}
