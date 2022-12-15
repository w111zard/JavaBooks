package com.example.javabooks.services;

import com.example.javabooks.Database;
import com.example.javabooks.entities.Cashier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CashierService {
    private final Connection connection = Database.connection;

    public int create(Cashier cashier) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("""
                INSERT INTO cashier
                (last_name, first_name, second_name, email, password)
                VALUES
                (?, ?, ?, ?, ?)
                """);

        preparedStatement.setString(1, cashier.getLastName());
        preparedStatement.setString(2, cashier.getFirstName());
        preparedStatement.setString(3, cashier.getSecondName());
        preparedStatement.setString(4, cashier.getEmail());
        preparedStatement.setString(5, cashier.getPassword());

        return preparedStatement.executeUpdate();
    }

    public ObservableList<Cashier> findAll() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cashier");
        ResultSet resultSet = preparedStatement.executeQuery();

        ObservableList<Cashier> list = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            String secondName = resultSet.getString("second_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            list.add(new Cashier(id, lastName, firstName, secondName, email, password));
        }

        return list;
    }

    public Cashier findOneByEmail(String _email) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM cashier WHERE email = '%s'", _email));

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            String secondName = resultSet.getString("second_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            return new Cashier(id, lastName, firstName, secondName, email, password);
        }

        return null;
    }

    public Cashier findOneById(Integer _id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM cashier WHERE id = %d", _id));

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            String secondName = resultSet.getString("second_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            return new Cashier(id, lastName, firstName, secondName, email, password);
        }

        return null;
    }

    public int update(Cashier cashier) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(String.format("""
                UPDATE cashier
                SET
                last_name = '%s',
                first_name = '%s',
                second_name = '%s',
                email = '%s',
                password = '%s'
                WHERE id = %d
                """,
                cashier.getLastName(),
                cashier.getFirstName(),
                cashier.getSecondName(),
                cashier.getEmail(),
                cashier.getPassword(),
                cashier.getId()));
    }

    public int delete(Integer id) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(String.format("DELETE FROM cashier WHERE id = %d", id));
    }
}
