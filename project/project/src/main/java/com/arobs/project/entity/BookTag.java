package com.arobs.project.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookTag)) return false;
        BookTag bookTag = (BookTag) o;
        return Objects.equals(getBook(), bookTag.getBook()) &&
                Objects.equals(getTag(), bookTag.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook(), getTag());
    }

    @Override
    public String toString() {
        return "BookTag{" +
                "book=" + book +
                ", tag=" + tag +
                '}';
    }
}
