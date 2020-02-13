package entities;

import java.sql.Date;
import java.util.List;


public class Book {

    private int bookId;
    private String bookName;
    private Date datePublished;
    private int noChapters;
    private List<Author> authorList;

    public Book(int bookId, String bookName, Date datePublished, int noChapters) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.datePublished = datePublished;
        this.noChapters = noChapters;
    }
    public Book( String bookName, Date datePublished, int noChapters) {
        this.bookName = bookName;
        this.datePublished = datePublished;
        this.noChapters = noChapters;
    }
    public Book(){}

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public int getNoChapters() {
        return noChapters;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public void setNoChapters(int noChapters) {
        this.noChapters = noChapters;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
