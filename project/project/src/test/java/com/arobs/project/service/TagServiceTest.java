package com.arobs.project.service;

import com.arobs.project.entity.Tag;
import com.arobs.project.repository.TagRepository;
import com.arobs.project.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @InjectMocks
    TagServiceImpl tagService;

    @Mock
    TagRepository tagRepository;

    //not working
    @Test
    void whenInsertTag_givenTag_returnTag() {
        Tag tagToInsert = new Tag(0, "testTag");
        Tag insertedTag = tagToInsert;
        insertedTag.setTagId(20);
        when(tagRepository.insertTag(tagToInsert)).thenReturn(insertedTag);
        Tag tag = tagService.insertTag("testTag");
        assertEquals(tag, insertedTag);
    }


}
