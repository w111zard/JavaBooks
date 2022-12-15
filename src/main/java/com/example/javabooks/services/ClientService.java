package com.example.javabooks.services;

import com.example.javabooks.Database;
import com.example.javabooks.entities.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ClientService {
    private final Connection connection = Database.connection;

    public int create(Client client) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO client
                    (last_name, first_name, second_name, email, address)
                    VALUES
                    (?, ?, ?, ?, ?)
                    """);

            preparedStatement.setString(1, client.getLastName());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getSecondName());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getAddress());

            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
            return 0;
        }
    }

    public ObservableList<Client> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client");
            ResultSet resultSet = preparedStatement.executeQuery();

            ObservableList<Client> list = FXCollections.observableArrayList();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                list.add(new Client(id, lastName, firstName, secondName, email, address));
            }

            return list;
        } catch (SQLException exception) {
            System.out.println(exception);
            return null;
        }
    }

    public Client findOneById(Integer _id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM client WHERE id = %d", _id));

            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String secondName = resultSet.getString("second_name");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");

                return new Client(id, lastName, firstName, secondName, email, address);
            }
            return null;
        } catch (SQLException exception) {
            System.out.println(exception);
            return null;
        }
    }

    public Client findOneByEmail(String _email) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM client WHERE email = '%s'", _email));

        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String lastName = resultSet.getString("last_name");
            String firstName = resultSet.getString("first_name");
            String secondName = resultSet.getString("second_name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");

            return new Client(id, lastName, firstName, secondName, email, address);
        }

        return null;
    }

    public int update(Client client) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(String.format("""
                            UPDATE client
                            SET
                            last_name = '%s',
                            first_name = '%s',
                            second_name = '%s',
                            email = '%s',
                            address = '%s'
                            WHERE id = %d
                            """,
                    client.getLastName(),
                    client.getFirstName(),
                    client.getSecondName(),
                    client.getEmail(),
                    client.getAddress(),
                    client.getId()));
        } catch (SQLException exception) {
            System.out.println(exception);
            return 0;
        }
    }

    public int delete(Integer id) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(String.format("DELETE FROM client WHERE id = %d", id));
        } catch (SQLException exception) {
            System.out.println(exception);
            return 0;
        }
    }
}
