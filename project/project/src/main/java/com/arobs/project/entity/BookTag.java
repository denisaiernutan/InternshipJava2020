package com.arobs.project.entity;

public class BookTag {

    private int bookId;
    private int tagId;

    public BookTag(int bookId, int tagId) {
        this.bookId = bookId;
        this.tagId = tagId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
