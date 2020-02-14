package services;

import entities.Author;
import entities.Book;
import repositories.AuthorRepository;
import repositories.BookRepository;

import java.sql.SQLException;
import java.util.List;

public class AuthorService {

    public static List<Author> getAllAuthors() throws SQLException {
        return AuthorRepository.getAllAuthors();
    }

    public static Author getAuthorByName(String authorName) throws SQLException {
        Author author = AuthorRepository.getAuthorByName(authorName);
        List<Book> bookList = BookRepository.findBooksByAuthor(author);
        author.setBookList(bookList);
        return author;

    }
}
