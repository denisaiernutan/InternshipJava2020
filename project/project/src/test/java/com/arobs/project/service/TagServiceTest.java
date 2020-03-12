package com.arobs.project.service;

import com.arobs.project.entity.Tag;
import com.arobs.project.repository.RepoFactory;
import com.arobs.project.repository.hibernate.HibernateRepoFactory;
import com.arobs.project.repository.hibernate.TagHibernateRepo;
import com.arobs.project.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @InjectMocks
    TagServiceImpl tagService;

    @Mock
    TagHibernateRepo tagRepository;

    @Mock
    RepoFactory repoFactory;

    @Mock
    HibernateRepoFactory hibernateRepoFactory;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(repoFactory.getInstance()).thenReturn(hibernateRepoFactory);
        when(repoFactory.getInstance().getTagRepository()).thenReturn(tagRepository);
        this.tagService.init();
    }

    @Test
    void whenInsertTag_givenTag_returnTag() {
        Tag insertedTag = new Tag(20, "testTag");
        when(tagRepository.insertTag(any(Tag.class))).thenReturn(insertedTag);
        Tag tag = tagService.insertTag("testTag");
        assertEquals(tag, insertedTag);
    }

}
