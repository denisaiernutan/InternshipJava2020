package services;

import entities.Book;
import repositories.BookRepository;

import java.sql.SQLException;
import java.util.List;

public class BookService {

    public static List<Book> getAllBooks(){
       return  BookRepository.getAllBooks();
    }

    public static void insertBook(Book book) throws SQLException, ClassNotFoundException {
         BookRepository.insertBook(book);
    }
}
