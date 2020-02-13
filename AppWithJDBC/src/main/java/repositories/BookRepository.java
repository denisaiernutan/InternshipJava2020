package repositories;

import connection.ConnectionClass;
import connection.DataSource;
import entities.Author;
import entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {


    public static List<Book> getAllBooks() throws SQLException {
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

    public static List<Book> getAllBooksWithAuthors() throws SQLException {
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


    public static void insertBook(Book book) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO books(book_name,date_published,no_chapters) VALUES (?,?,?)";
        try (Connection connection = DataSource.getConnection(); PreparedStatement insertStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, book.getBookName());
            insertStmt.setDate(2, book.getDatePublished());
            insertStmt.setInt(3, book.getNoChapters());

            insertStmt.executeUpdate();
            ResultSet rs = insertStmt.getGeneratedKeys();
            if (rs.next()) {
                book.setBookId(insertStmt.getGeneratedKeys().getInt(1));
            }
        }
    }

    private static Book findBookById(int bookId) throws SQLException {
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

    public static List<Book> findBooksByAuthor(Author author) throws SQLException {
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

    public static void deleteBookByName(String bookName) throws SQLException {
        String sql = "DELETE FROM books WHERE book_name=?";
        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,bookName);
             int rs = statement.executeUpdate();
        }

    }

}
