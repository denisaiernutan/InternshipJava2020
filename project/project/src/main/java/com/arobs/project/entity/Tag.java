package com.arobs.project.entity;

public class Tag {

    private int tagId;

    private String tagDescription;

    public Tag(int tagId, String tagDescription) {
        this.tagId = tagId;
        this.tagDescription = tagDescription;
    }

    public Tag() {
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
