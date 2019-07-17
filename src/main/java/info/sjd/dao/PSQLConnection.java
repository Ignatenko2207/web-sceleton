package info.sjd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PSQLConnection {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/step-qa-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "248842";


    protected static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
