package services;

import entities.Author;
import entities.Book;
import repositories.AuthorRepository;
import repositories.BookRepository;

import java.sql.SQLException;
import java.util.List;

public class AuthorService {

    AuthorRepository authorRepository= new AuthorRepository();
    BookRepository bookRepository= new BookRepository();
    public AuthorService() {
    }

    public  List<Author> getAllAuthors() throws SQLException {
        return authorRepository.getAllAuthors();
    }

    public  Author getAuthorByName(String authorName) throws SQLException {
        Author author = authorRepository.getAuthorByName(authorName);
        List<Book> bookList = bookRepository.findBooksByAuthor(author);
        author.setBookList(bookList);
        return author;

    }
}
