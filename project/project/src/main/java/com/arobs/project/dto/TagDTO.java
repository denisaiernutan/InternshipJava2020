package com.arobs.project.dto;

public class TagDTO {

    private int tagId;

    private String tagDescription;

    public TagDTO(int tagId, String tagDescription) {
        this.tagId = tagId;
        this.tagDescription = tagDescription;
    }

    public TagDTO() {
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }
}
