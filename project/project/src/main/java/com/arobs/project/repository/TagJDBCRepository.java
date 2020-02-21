package com.arobs.project.repository;

import com.arobs.project.entity.Employee;
import com.arobs.project.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class TagJDBCRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TagJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Tag findByDescription(String description) {
        String sql = "select * from tags where tag_description=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{description}, (rs, rowNum) ->
                    new Tag(rs.getInt("tag_id"),
                            rs.getString("tag_description")));
        }
        catch (Exception e){
            return null;
        }
    }

    public Tag insertTag(String description){
        String sql=" insert into tags(tag_description) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, description);
            return ps;
        }, keyHolder);

        return new Tag(Objects.requireNonNull(keyHolder.getKey()).intValue(), description);
    }

}
