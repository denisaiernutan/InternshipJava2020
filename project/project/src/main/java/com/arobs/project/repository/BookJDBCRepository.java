package com.arobs.project.repository;

import com.arobs.project.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class BookJDBCRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Book insertBook(Book book)
    {
        String sql = "insert into books(book_title,book_author,book_description,book_added_date) values(?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,book.getBookTitle());
            ps.setString(2,book.getBookAuthor());
            ps.setString(3, book.getBookDescription());
            ps.setTimestamp(4, book.getBookAddedDate());
            return ps;
        }, keyHolder);

        book.setBookId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return book;
    }
}
