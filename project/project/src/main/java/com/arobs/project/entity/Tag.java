package com.arobs.project.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private int tagId;

    @Column(name="tag_description")
    private String tagDescription;

    @ManyToMany(mappedBy = "tagSet")
    private Set<Book> bookSet = new HashSet<>();

    public Tag() {
    }

    public Tag(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public Tag(int tagId, String tagDescription) {
        this.tagId = tagId;
        this.tagDescription = tagDescription;
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

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    public void addBook(Book book){
        this.bookSet.add(book);
        book.getTagSet().add(this);
    }

}
