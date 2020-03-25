package com.arobs.project.controller;

import com.arobs.project.converter.BookConverter;
import com.arobs.project.converter.TagConverter;
import com.arobs.project.entity.Book;
import com.arobs.project.entity.Tag;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tags")
public class TagController {

    private TagService tagService;
    private static final Logger logger = LoggerFactory.getLogger("FILE");


    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping(value = "/{descr}")
    public ResponseEntity<?> insertTag(@PathVariable("descr") @NotNull String description) {
        Tag insertedTag = tagService.insertTag(description);
        return new ResponseEntity<>(TagConverter.convertToTagWithIdDTO(insertedTag), HttpStatus.OK);
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
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
