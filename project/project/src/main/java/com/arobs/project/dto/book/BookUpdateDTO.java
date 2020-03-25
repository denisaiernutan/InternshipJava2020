package com.arobs.project.dto.book;

import com.arobs.project.dto.tag.TagDTO;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class BookUpdateDTO {

    @NotNull
    private int bookId;

    @NotNull
    private String bookTitle;

    @NotNull
    private String bookAuthor;

    @NotNull
    private String bookDescription;

    @NotNull
    private Set<TagDTO> tagSet;

    public BookUpdateDTO(int bookId, String bookTitle, String bookAuthor, String bookDescription, Set<TagDTO> tagSet) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.tagSet = tagSet;
    }

    public BookUpdateDTO() {
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Set<TagDTO> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<TagDTO> tagSet) {
        this.tagSet = tagSet;
    }
}
