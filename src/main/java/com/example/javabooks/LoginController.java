package com.example.javabooks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Button exitButton;
    @FXML
    private Label usernameErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    public void loginButtonOnAction(ActionEvent action) throws SQLException {
        if (usernameTextField.getText().isBlank()) {
            usernameErrorLabel.setText("Не может быть пустым");
            return;
        }

        if (usernameTextField.getText().length() < 4) {
            usernameErrorLabel.setText("Минимальная длина 4 символа");
            return;
        }

        if (usernameTextField.getText().length() > 12) {
            usernameErrorLabel.setText("Максимальная длина 12 символов");
            return;
        }

        if (passwordTextField.getText().isBlank()) {
            passwordErrorLabel.setText("Не может быть пустым");
            return;
        }

        if (usernameTextField.getText().length() < 4) {
            passwordErrorLabel.setText("Минимальная длина 4 символа");
            return;
        }

        if (usernameTextField.getText().length() > 12) {
            passwordErrorLabel.setText("Максимальная длина 100 символов");
            return;
        }

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        Statement statement = connection.createStatement();

        String query = String.format("""
                SELECT * FROM "salesman" WHERE
                email = '%s'
                AND
                password = '%s'
                """, usernameTextField.getText(), passwordTextField.getText());

        ResultSet resultSet = statement.executeQuery(query);

        if (! resultSet.next()) {
            usernameErrorLabel.setText("Неверное имя и пароль");
            return;
        }

        usernameErrorLabel.setText("Добро пожаловать!");
    }

    public void exitButtonOnClick(ActionEvent action) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
