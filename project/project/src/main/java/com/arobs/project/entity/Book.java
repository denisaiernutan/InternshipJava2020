package com.arobs.project.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_description")
    private String bookDescription;

    @Column(name = "book_added_date")
    private Timestamp bookAddedDate;

    @ManyToMany
    @JoinTable(name = "book_tag", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tagSet = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<Copy> copySet = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<BookRent> bookRentSet = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<RentRequest> rentRequestSet = new HashSet<>();

    public Book(int bookId, String bookTitle, String bookAuthor, String bookDescription, Timestamp bookAddedDate) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookAddedDate = bookAddedDate;
    }

    public Book() {
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

    public Timestamp getBookAddedDate() {
        return bookAddedDate;
    }

    public void setBookAddedDate(Timestamp bookAddedDate) {
        this.bookAddedDate = bookAddedDate;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    public void addTag(Tag tag) {
        this.tagSet.add(tag);
        tag.getBookSet().add(this);
    }

    public void removeTag(Tag tag) {
        this.tagSet.remove(tag);
        tag.getBookSet().remove(this);
    }

    public void addCopy(Copy copy) {
        this.copySet.add(copy);
        copy.setBook(this);
    }

    public void addBookRent(BookRent bookRent) {
        this.bookRentSet.add(bookRent);
        bookRent.setBook(this);
    }

    public void addRentRequest(RentRequest rentRequest) {
        this.rentRequestSet.add(rentRequest);
        rentRequest.setBook(this);
    }

}
