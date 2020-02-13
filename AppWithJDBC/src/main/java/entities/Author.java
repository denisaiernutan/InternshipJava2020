package entities;

import java.sql.Date;
import java.util.List;

public class Author {

    private int authorId;
    private String authorName;
    private Date birthDate;
    private List<Book> bookList;

    public Author(int authorId, String authorName, Date birthDate) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.birthDate = birthDate;
    }

    public Author() { }

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
