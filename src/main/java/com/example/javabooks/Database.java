package com.example.javabooks;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    public static Connection connection = null;

    public static void start() throws SQLException {
        if (connection != null) {
            return;
        }

        String url = "jdbc:postgresql://localhost/javabooks";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "1234");
        connection = DriverManager.getConnection(url, props);
    }

    private Database() {}
}
