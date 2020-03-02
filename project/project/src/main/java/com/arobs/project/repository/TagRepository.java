package com.arobs.project.repository;

import com.arobs.project.entity.Tag;

import java.util.List;

public interface TagRepository {

    List<Tag> findByDescription(String description);

    Tag insertTag(Tag tag);

    boolean deleteTag(Tag tag);

    Tag findById(int tagId);
}
