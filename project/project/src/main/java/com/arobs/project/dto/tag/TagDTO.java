package com.arobs.project.dto.tag;

public class TagDTO {

    private String tagDescription;

    public TagDTO(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public TagDTO() {
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }
}
