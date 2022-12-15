package com.example.javabooks;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent parent;

    public void switchToScene(ActionEvent event, String fxmlFile, int w, int h) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), w, h);
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public void openNewScene(ActionEvent event, String fxmlFile, int w, int h) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), w, h);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public void closeScene(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
