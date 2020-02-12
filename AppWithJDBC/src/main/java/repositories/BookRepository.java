package repositories;

import connection.ConnectionClass;
import entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private static Statement stmt;

    private static PreparedStatement insertStmt;

    static {
        try {
            stmt = ConnectionClass.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        List<Book> bookList = new ArrayList<Book>(10);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String name = rs.getString("book_name");
                Date date = rs.getDate("date_published");
                int chapters = rs.getInt("no_chapters");

                Book book = new Book(id, name, date, chapters);
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static void insertBook(Book book) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO books(book_name,date_published,no_chapters) VALUES (?,?,?)";

        insertStmt = ConnectionClass.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
