package services;

import entities.Book;
import repositories.BookRepository;

import java.sql.SQLException;
import java.util.List;

public class BookService {

    BookRepository bookRepository= new BookRepository();
    public BookService() {
    }

    public  List<Book> getAllBooks() throws SQLException {
        return bookRepository.getAllBooks();
    }


    public  void insertBook(Book book, String authorName) throws SQLException, ClassNotFoundException {
        bookRepository.insertBook(book, authorName);
    }

    public  void deleteBookByName(String bookName) throws SQLException {
        bookRepository.deleteBookByName(bookName);
    }
}
