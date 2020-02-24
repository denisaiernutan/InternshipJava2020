package com.arobs.project.repository;

import com.arobs.project.entity.Tag;

public interface TagRepository {

    Tag findByDescription(String description);

    Tag insertTag(String description);
}
