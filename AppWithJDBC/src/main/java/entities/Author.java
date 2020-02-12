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

    public int getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
