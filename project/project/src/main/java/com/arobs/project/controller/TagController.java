package com.arobs.project.controller;

import com.arobs.project.dto.TagWithIdDTO;
import com.arobs.project.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean deleteTag(@RequestBody TagWithIdDTO tagWithIdDTO){
        return tagService.deleteTag(tagWithIdDTO);
    }
}
