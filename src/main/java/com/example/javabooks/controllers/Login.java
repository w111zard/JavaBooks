package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import com.example.javabooks.entities.Cashier;
import com.example.javabooks.entities.Client;
import com.example.javabooks.libs.Settings;
import com.example.javabooks.services.CashierService;
import com.example.javabooks.services.ClientService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Login implements Initializable {
    private CashierService cashierService;
    private SceneController sceneController;

    private static final int minimalFieldLength = 4;
    private static final int maximumFieldLength = 128;
    @FXML
    private Button exitButton;
    @FXML
    private Label usernameErrorLabel;
    @FXML
    private Label passwordErrorLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cashierService = new CashierService();
        sceneController = new SceneController();
    }

    public void loginButtonOnAction(ActionEvent action) throws SQLException, IOException {
        // Получаем данные из формы
        String email = emailTextField.getText();
        String password = passwordTextField.getText();

        // Проверяем что поле "email" не пустое и имеет допустимую длину.
        // Если поле не удовлетворяет условиям, то функция checkField
        // вернет строку с описанием ошибки,
        // если поле прошло проверку, то возращается null
        String emailErrorMessage = checkField(email);
        if (emailErrorMessage != null) {
            usernameErrorLabel.setText(emailErrorMessage); // Показываем описание ошибки пользователю
            return; // Завершаем работу, т.к введены неверные данные для входа
        }

        // Проверяем что поле "password" не пустое и имеет допустимую длину.
        // Если поле не удовлетворяет условиям, то функция checkField
        // вернет строку с описанием ошибки,
        // если поле прошло проверку, то возращается null
        String passwordErrorMessage = checkField(password);
        if (passwordErrorMessage != null) {
            passwordErrorLabel.setText(passwordErrorMessage);  // Показываем описание ошибки пользователю
            return; // Завершаем работу, т.к введены неверные данные для входа
        }

        // Проверяем правильность email и пароля.
        // Если данные неверны, то функция checkCredentials возвращает
        // строку с описанием ошибки.
        // Если данные верны, то возвращается null
        String credentialsErrorMessage = checkCredentials(email, password);
        if (credentialsErrorMessage != null) {
            // Показываем описание ошибки пользователю
            usernameErrorLabel.setText(credentialsErrorMessage);
            passwordErrorLabel.setText(credentialsErrorMessage);
            return; // Завершаем работу, т.к введены неверные данные для входа
        }

        // Создаем сессию для кассира
        Cashier cashier = cashierService.findOneByEmail(email);
        Session.setCashier(cashier);

        // Были введены правильные данные, выполняем переход на другую форму
        sceneController.switchToScene(action, "profile.fxml", Settings.MAIN_WINDOW_W, Settings.MAIN_WINDOW_H);
    }

    private String checkField(String input) {
        /*
            Проверяет что введенные данные не пустые и имеют допустимую длину.
            В случае несоответсвия данных возвращает описание с ошибкой.
            В случае корректности данных возвращает null.
         */

        if (input.isBlank()) {
            return "Не может быть пустым";
        }

        if (input.length() < minimalFieldLength) {
            return String.format("Минимальная длина %d символа", minimalFieldLength);
        }

        if (input.length() > maximumFieldLength) {
            return String.format("Максимальная длина %d символов", maximumFieldLength);
        }

        return null;
    }

    private String checkCredentials(String email, String password) throws SQLException {
        /*
            Проверяет что введенные email и пароль правильные.
            В случае несоответсвия данных возвращает описание с ошибкой.
            В случае корректности данных возвращает null.
         */

        Cashier cashier = cashierService.findOneByEmail(email);
        if (cashier == null) {
            return "Неверные данные";
        }

        String foundPassword = cashier.getPassword();
        if (! foundPassword.equals(password)) {
            return "Неверные данные";
        }

        return null;
    }

    public void exitButtonOnClick(ActionEvent action) {
        /*
            Закрывает приложение
         */
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
