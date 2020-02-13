package connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl( "jdbc:mysql://localhost/booksdb" );
        config.setUsername( "root" );
        config.setPassword( "nutella" );
        ds = new HikariDataSource( config );
    }

    public DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
