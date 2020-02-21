package com.arobs.project.repository;


import com.arobs.project.entity.BookTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class BookTagJDBCRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookTagJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BookTag insertBookTag(BookTag bookTag) {
        String sql = "insert into book_tag(book_id,tag_id) values(?,?)";

        jdbcTemplate.update(sql, bookTag.getBookId(), bookTag.getTagId());

        return bookTag;


    }


}
