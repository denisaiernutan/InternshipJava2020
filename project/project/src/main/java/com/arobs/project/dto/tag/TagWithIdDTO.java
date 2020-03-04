package com.arobs.project.dto.tag;

public class TagWithIdDTO extends TagDTO {

    private int tagId;


    public TagWithIdDTO(int tagId, String tagDescription) {
        super(tagDescription);
        this.tagId = tagId;
    }

    public TagWithIdDTO() {
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
