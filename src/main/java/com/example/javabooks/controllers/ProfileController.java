package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import com.example.javabooks.libs.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label secondNameLabel;

    private SceneController sceneController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneController = new SceneController();

        lastNameLabel.setText(Session.getCashier().getLastName());
        firstNameLabel.setText(Session.getCashier().getFirstName());
        secondNameLabel.setText(Session.getCashier().getSecondName());
    }

    @FXML
    void onButtonBookClick(ActionEvent event) {
        sceneController.switchToScene(event, "book.fxml", Settings.MAIN_WINDOW_W, Settings.MAIN_WINDOW_H);
    }

    @FXML
    void onButtonClientClick(ActionEvent event) {
        sceneController.switchToScene(event, "client.fxml", Settings.MAIN_WINDOW_W, Settings.MAIN_WINDOW_H);
    }

    @FXML
    void onButtonProfileClick(ActionEvent event) {
        // Ничего не делаем, т.к мы уже находимся в этом окне
    }

    @FXML
    void onButtonOrderClick(ActionEvent event) {
        sceneController.switchToScene(event, "order.fxml", Settings.MAIN_WINDOW_W, Settings.MAIN_WINDOW_H);
    }

    @FXML
    void onButtonExitClick(ActionEvent event) {
        sceneController.closeScene(event); // Закрываем окно
    }
}
