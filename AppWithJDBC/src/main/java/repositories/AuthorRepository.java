package repositories;

import connection.ConnectionClass;
import entities.Author;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {

    private static Statement stmt;

    static {
        try {
            stmt = ConnectionClass.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Author> getAllAuthors(){
        String sql= "SELECT * FROM authors";
        List<Author> authorList= new ArrayList<Author>(10);
        try {
            ResultSet rs= stmt.executeQuery(sql);
            while(rs.next()){
                int id= rs.getInt("author_id");
                String name= rs.getString("author_name");
                Date date= rs.getDate("birth_date");

                Author author= new Author(id,name,date);
                authorList.add(author);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }


}
