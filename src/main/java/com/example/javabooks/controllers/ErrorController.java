package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController implements Initializable {
    @FXML
    private Text errorDescription;

    @FXML
    private Button okButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorDescription.setText(Session.getErrorMessage());
    }

    @FXML
    void onOkButtonClick(ActionEvent event) {
        SceneController sceneController = new SceneController();
        sceneController.closeScene(event);
    }
}
