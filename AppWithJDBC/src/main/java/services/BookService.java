package services;

import entities.Book;
import repositories.BookRepository;

import java.sql.SQLException;
import java.util.List;

public class BookService {

    public static List<Book> getAllBooks() throws SQLException {
        return BookRepository.getAllBooks();
    }


    public static void insertBook(Book book, String authorName) throws SQLException, ClassNotFoundException {
        BookRepository.insertBook(book, authorName);
    }

    public static void deleteBookByName(String bookName) throws SQLException {
        BookRepository.deleteBookByName(bookName);
    }
}
