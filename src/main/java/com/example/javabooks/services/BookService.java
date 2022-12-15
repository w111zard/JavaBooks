package com.example.javabooks.services;

import com.example.javabooks.Database;
import com.example.javabooks.entities.Book;
import com.example.javabooks.entities.Cashier;
import com.example.javabooks.entities.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class BookService {
    private final Connection connection = Database.connection;

    public int create(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO book
                    (author, title, genre, price)
                    VALUES
                    (?, ?, ?, ?)
                    """);

            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setFloat(4, book.getPrice());

            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
            return 0;
        }
    }

    public Book findOneById(Integer _id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM book WHERE id = %d", _id));

            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                Float price = resultSet.getFloat("price");

                return new Book(id, author, title, genre, price);
            }
            return null;
        } catch (SQLException exception) {
            System.out.println(exception);
            return null;
        }
    }

    public ObservableList<Book> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book");
            ResultSet resultSet = preparedStatement.executeQuery();

            ObservableList<Book> list = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                Float price = resultSet.getFloat("price");
                list.add(new Book(id, author, title, genre, price));
            }
            return list;
        } catch (SQLException exception) {
            System.out.println(exception);
            return null;
        }
    }

    public int update(Book book) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(String.format("""
                            UPDATE book
                            SET
                            author = '%s',
                            title = '%s',
                            genre = '%s',
                            price = %f
                            WHERE
                            id = %d
                            """,
                    book.getAuthor(),
                    book.getTitle(),
                    book.getGenre(),
                    book.getPrice(),
                    book.getId()));
        } catch (SQLException exception) {
            System.out.println(exception);
            return 0;
        }
    }

    public int delete(Integer id) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(String.format("DELETE FROM book WHERE id = %d", id));
        } catch (SQLException exception) {
            System.out.println(exception);
            return 0;
        }
    }
}
