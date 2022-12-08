package com.example.javabooks;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    public Connection connection;

    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/javabooks";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "1234");
        return DriverManager.getConnection(url, props);
    }
}
