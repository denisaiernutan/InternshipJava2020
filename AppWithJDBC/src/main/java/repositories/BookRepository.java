package repositories;

import connection.ConnectionClass;
import connection.DataSource;
import entities.Author;
import entities.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.BookServlet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private static final Logger logger
            = LoggerFactory.getLogger(BookServlet.class);

    AuthorRepository authorRepository= new AuthorRepository();

    public BookRepository() {
    }

    public  List<Book> getAllBooks() throws SQLException {
        String sql = "SELECT * FROM books";
        try (Connection connection = DataSource.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            List<Book> bookList = new ArrayList<Book>(10);
            try (ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("book_id");
                    String name = rs.getString("book_name");
                    Date date = rs.getDate("date_published");
                    int chapters = rs.getInt("no_chapters");

                    Book book = new Book(id, name, date, chapters);
                    bookList.add(book);
                }
            }
            return bookList;
        }
    }

    public  List<Book> getAllBooksWithAuthors() throws SQLException {
        String sql = "SELECT * FROM books";
        try (Connection connection = DataSource.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            List<Book> bookList = new ArrayList<Book>(10);
            try (ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("book_id");
                    String name = rs.getString("book_name");
                    Date date = rs.getDate("date_published");
                    int chapters = rs.getInt("no_chapters");

                    Book book = new Book(id, name, date, chapters);
                    bookList.add(book);
                }
            }
            return bookList;
        }
    }


    public  void insertBook(Book book, String authorName) throws SQLException {

        String sql = "INSERT INTO books(book_name,date_published,no_chapters) VALUES (?,?,?)";
        int bookId = -1;
        try (Connection connection = DataSource.getConnection(); PreparedStatement insertStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, book.getBookName());
            insertStmt.setDate(2, book.getDatePublished());
            insertStmt.setInt(3, book.getNoChapters());

            insertStmt.executeUpdate();
            try (ResultSet rs = insertStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    bookId = rs.getInt(1);
                    logger.info("generated key: " + bookId);
                }
                book.setBookId(bookId);
            }

            Author author = authorRepository.getAuthorByName(authorName);
            sql = "INSERT INTO books_authors(book_id, author_id) VALUES (?,?)";
            try (PreparedStatement insertStm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                insertStm.setInt(1, book.getBookId());
                insertStm.setInt(2, author.getAuthorId());
                insertStm.executeUpdate();
            }
        }

    }

    private  Book findBookById(int bookId) throws SQLException {
        String sql = "SELECT * FROM books where book_id=?";
        Book toReturn = new Book();
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);

            ResultSet rs = statement.executeQuery();
            rs.next();
            toReturn.setBookId(rs.getInt("book_id"));
            toReturn.setBookName(rs.getString("book_name"));
            toReturn.setDatePublished(rs.getDate("date_published"));
            toReturn.setNoChapters(rs.getInt("no_chapters"));

        }
        return toReturn;
    }

    public  List<Book> findBooksByAuthor(Author author) throws SQLException {
        List<Book> bookList = new ArrayList<>(15);
        String sql = "SELECT * FROM books_authors where author_id=?";
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, author.getAuthorId());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Book book = findBookById(rs.getInt("book_id"));
                bookList.add(book);
            }
        }
        return bookList;
    }

    public  void deleteBookByName(String bookName) throws SQLException {
        String sql = "DELETE FROM books WHERE book_name=?";
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookName);
            statement.executeUpdate();
        }

    }

}
