package com.example.javabooks.services;

import com.example.javabooks.Database;
import com.example.javabooks.entities.Client;
import com.example.javabooks.entities.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class OrderService {
    private final Connection connection = Database.connection;

    public int create(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO "order"
                    (client_id, cashier_id, book_id, date)
                    VALUES
                    (?, ?, ?, ?)
                    """);

            preparedStatement.setInt(1, order.getClientId());
            preparedStatement.setInt(2, order.getCashierId());
            preparedStatement.setInt(3, order.getBookId());
            preparedStatement.setString(4, order.getDate());

            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
            return 0;
        }
    }

    public ObservableList<Order> findAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("""
                SELECT * FROM "order"
                """);
        ResultSet resultSet = preparedStatement.executeQuery();

        ObservableList<Order> list = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer clientId = resultSet.getInt("client_id");
            Integer cashierId = resultSet.getInt("cashier_id");
            Integer bookId = resultSet.getInt("book_id");
            String date = resultSet.getString("date");
            list.add(new Order(id, clientId, cashierId, bookId, date));
        }

        return list;
    }

    public int update(Order order) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(String.format("""
                            UPDATE "order"
                            SET
                            client_id = %d,
                            cashier_id = %d,
                            book_id = %d,
                            date = '%s'
                            WHERE id = %d
                            """,
                    order.getClientId(),
                    order.getCashierId(),
                    order.getBookId(),
                    order.getDate(),
                    order.getId()));
        } catch (SQLException exception) {
            System.out.println(exception);
            return 0;
        }
    }

    public int delete(Integer id) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(String.format("""
                DELETE FROM "order" WHERE id = %d
            """, id));
        } catch (SQLException exception) {
            System.out.println(exception);
            return 0;
        }
    }
}
