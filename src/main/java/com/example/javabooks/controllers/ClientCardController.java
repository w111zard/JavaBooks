package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import com.example.javabooks.entities.Client;
import com.example.javabooks.libs.Validator;
import com.example.javabooks.services.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCardController implements Initializable {
    @FXML
    private Label addressErrorLabel;

    @FXML
    private TextField addressField;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private TextField emailField;

    @FXML
    private Label firstNameErrorLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private Label lastNameErrorLabel;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label secondNameErrorLabel;

    @FXML
    private TextField secondNameField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    private SceneController sceneController;
    private ClientService clientService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneController = new SceneController();
        clientService = new ClientService();

        Client client = Session.getClient();
        if (client != null) {
            lastNameField.setText(client.getLastName());
            firstNameField.setText(client.getFirstName());
            secondNameField.setText(client.getSecondName());
            emailField.setText(client.getEmail());
            addressField.setText(client.getAddress());
        }
    }

    @FXML
    void OnAddButtonClick(ActionEvent event) {
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String secondName = secondNameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();

        if (! checkFields(lastName, firstName, secondName, email, address)) {
            return;
        }

        if (Session.getClient() == null) {
            Client client = new Client(0, lastName, firstName, secondName, email, address);
            clientService.create(client);
        }
        else {
            Client client = new Client(Session.getClient().getId(), lastName, firstName, secondName, email, address);
            clientService.update(client);
        }

        sceneController.closeScene(event);
    }

    private boolean checkFields(String lastName, String firstName, String secondName,
                                String email, String address) {

        String errorDescription;
        Boolean isValid = true;

        errorDescription = Validator.checkText(lastName);
        if (errorDescription != null) {
            lastNameErrorLabel.setText(errorDescription);
            isValid = false;
        }

        errorDescription = Validator.checkText(firstName);
        if (errorDescription != null) {
            firstNameErrorLabel.setText(errorDescription);
            isValid = false;
        }

        errorDescription = Validator.checkText(secondName);
        if (errorDescription != null) {
            secondNameErrorLabel.setText(errorDescription);
            isValid = false;
        }

        errorDescription = Validator.checkEmail(email);
        if (errorDescription != null) {
            emailErrorLabel.setText(errorDescription);
            isValid = false;
        }

        errorDescription = Validator.checkText(address);
        if (errorDescription != null) {
            addressErrorLabel.setText(errorDescription);
            isValid = false;
        }

        return isValid;
    }
    @FXML
    void onCancelButtonClick(ActionEvent event) {
        sceneController.closeScene(event);
    }
}
