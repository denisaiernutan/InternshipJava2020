package com.arobs.project.controller;

import com.arobs.project.dto.tag.TagWithIdDTO;
import com.arobs.project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return tagService.insertTag(description);
    }

    @DeleteMapping
    public boolean deleteTag(@RequestBody int tagId) {
        return tagService.deleteTag(tagId);
    }

    @GetMapping("findBooks")
    public ResponseEntity<?> getBooks(@RequestParam int tagId) {
        return new ResponseEntity<>(tagService.findBooks(tagId), HttpStatus.OK);
    }
}
