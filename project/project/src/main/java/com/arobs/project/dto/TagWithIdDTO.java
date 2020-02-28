package com.arobs.project.dto;

public class TagWithIdDTO {

    private int tagId;
    private String tagDescription;

    public TagWithIdDTO(int tagId, String tagDescription) {
        this.tagId = tagId;
        this.tagDescription = tagDescription;
    }

    public TagWithIdDTO() {
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
