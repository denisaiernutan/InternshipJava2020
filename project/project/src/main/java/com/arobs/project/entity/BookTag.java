package com.arobs.project.entity;

public class BookTag {

    private Book book;

    private Tag tag;

    public BookTag(Book book, Tag tag) {
        this.book = book;
        this.tag = tag;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
