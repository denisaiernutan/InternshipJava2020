package com.arobs.project.controller;

import com.arobs.project.entity.Book;
import com.arobs.project.entity.Tag;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.impl.TagServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {

    @MockBean
    TagServiceImpl tagService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void whenDeleteTag_givenTagId_returnBoolean() throws Exception {
        when(tagService.deleteTag(any(int.class))).thenReturn(true);
        MvcResult mvcResult = mockMvc.perform(delete("/tags").param("tagId", String.valueOf(4))).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().equals("true"));
    }

    @Test
    void whenInsertTag_givenDescription_returnResponseEntity() throws Exception {
        when(tagService.insertTag(any(String.class))).thenReturn(new Tag(20, "testTag"));
        MvcResult mvcResult = mockMvc.perform(post("/tags/{descr}", " testTag")).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("testTag"));
    }

    @Test
    void whenGetBooks_givenTagId_returnBookLis() throws Exception {
        List<Book> bookList = new ArrayList<>(5);

        when(tagService.findBooks(any(int.class))).thenReturn(bookList);

        mockMvc.perform(get("/tags/findBooks").param("tagId", String.valueOf(4)))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void whenGetBooks_givenTagId_throwValidationException() throws Exception {
        when(tagService.findBooks(any(int.class))).thenThrow(new ValidationException("smth went wrong"));

        mockMvc.perform(get("/tags/findBooks").param("tagId", String.valueOf(4)))
                .andExpect(status().isBadRequest()).andReturn();

    }

}
