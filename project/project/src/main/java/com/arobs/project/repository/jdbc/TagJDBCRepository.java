package com.arobs.project.repository.jdbc;

import com.arobs.project.entity.Tag;
import com.arobs.project.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TagJDBCRepository implements TagRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TagJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tag> findByDescription(String description) {
        String sql = "select * from tags where tag_description=?";
        List<Tag> tags = new ArrayList<>(5);
        tags.add(jdbcTemplate.queryForObject(sql, new Object[]{description}, (rs, rowNum) ->
                new Tag(rs.getInt("tag_id"),
                        rs.getString("tag_description"))));

        return tags;
    }

    public Tag insertTag(Tag tag) {
        String sql = " insert into tags(tag_description) values (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tag.getTagDescription());
            return ps;
        }, keyHolder);

        return new Tag(Objects.requireNonNull(keyHolder.getKey()).intValue(), tag.getTagDescription());
    }

}
