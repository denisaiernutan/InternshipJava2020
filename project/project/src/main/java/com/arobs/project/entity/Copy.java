package com.arobs.project.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "copies")
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "copy_id")
    private int copyId;

    @Column(name = "copy_flag")
    private boolean copyFlag;

    @Column(name = "copy_status")
    private String copyStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "copy")
    private Set<BookRent> bookRentSet;

    public Copy() {
    }


    public Copy(int copyId, boolean copyFlag, String copyStatus, Book book) {
        this.copyId = copyId;
        this.copyFlag = copyFlag;
        this.copyStatus = copyStatus;
        this.book = book;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }

    public boolean isCopyFlag() {
        return copyFlag;
    }

    public void setCopyFlag(boolean copyFlag) {
        this.copyFlag = copyFlag;
    }

    public String getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(String copyStatus) {
        this.copyStatus = copyStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void addBookRent(BookRent bookRent) {
        this.bookRentSet.add(bookRent);
    }
}
