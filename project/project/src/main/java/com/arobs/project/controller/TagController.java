package com.arobs.project.controller;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.converter.TagConverter;
import com.arobs.project.dto.tag.TagWithIdDTO;
import com.arobs.project.entity.Book;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping(value = "/{descr}")
    public TagWithIdDTO insertTag(@PathVariable("descr") String description) {
        return TagConverter.convertToTagWithIdDTO(tagService.insertTag(description));
    }

    @DeleteMapping
    public boolean deleteTag(@RequestParam int tagId) {
        return tagService.deleteTag(tagId);
    }

    @GetMapping("findBooks")
    public ResponseEntity<?> getBooks(@RequestParam int tagId) {
        try {
            List<Book> bookList = tagService.findBooks(tagId);

            return new ResponseEntity<>(bookList.stream()
                    .map(BookConverter::convertToDTO)
                    .collect(Collectors.toList()), HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
