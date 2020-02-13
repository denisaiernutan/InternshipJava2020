package repositories;

import connection.ConnectionClass;
import connection.DataSource;
import entities.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {


    public static List<Author> getAllAuthors() throws SQLException {
        String sql = "SELECT * FROM authors";
        try (Connection connection = DataSource.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            List<Author> authorList = new ArrayList<Author>(10);
            try (ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("author_id");
                    String name = rs.getString("author_name");
                    Date date = rs.getDate("birth_date");

                    Author author = new Author(id, name, date);
                    authorList.add(author);
                }
            }
            return authorList;
        }
    }

    public static Author getAuthorByName(String authorName) throws SQLException {
        String sql = "SELECT * from authors where author_name=?";
        Author toReturn = new Author();

        try (Connection connection = DataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, authorName);

            ResultSet rs = statement.executeQuery();
            rs.next();
            toReturn.setAuthorId(rs.getInt("author_id"));
            toReturn.setAuthorName(rs.getString("author_name"));
            toReturn.setBirthDate(rs.getDate("birth_date"));

        }

        return toReturn;
    }
}
