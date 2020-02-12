package connection;

import java.sql.*;

public class ConnectionClass {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/booksdb";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "nutella";

    static  Connection conn=null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(conn==null) {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return conn;


    }




}
