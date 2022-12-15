package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import com.example.javabooks.entities.Cashier;
import com.example.javabooks.entities.Client;
import com.example.javabooks.services.ClientService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Main implements Initializable {
    @FXML
    private Button exitButton;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label secondNameLabel;

    @FXML
    private TableView<Client> tableClients;
    @FXML
    private TableColumn<Client, String> columnClientAddress;

    @FXML
    private TableColumn<Client, String> columnClientEmail;

    @FXML
    private TableColumn<Client, String> columnClientFirstName;

    @FXML
    private TableColumn<Client, Integer> columnClientId;

    @FXML
    private TableColumn<Client, String> columnClientLastName;

    @FXML
    private TableColumn<Client, String> columnClientSecondName;

    private ClientService clientService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientService = new ClientService();

        lastNameLabel.setText(Session.getCashier().getLastName());
        firstNameLabel.setText(Session.getCashier().getFirstName());
        secondNameLabel.setText(Session.getCashier().getSecondName());

        // Table clients
        columnClientId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        columnClientLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        columnClientFirstName.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        columnClientSecondName.setCellValueFactory(new PropertyValueFactory<Client, String>("secondName"));
        columnClientEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        columnClientAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));

        clientsTableRefresh();
    }

    private void clientsTableRefresh() {
        ClientService clientService = new ClientService();

        tableClients.setItems(clientService.findAll());
    }

    public void onCreateButtonClick(ActionEvent actionEvent) {

    }

    public void onEditButtonClick(ActionEvent actionEvent) {
        ObservableList<Client> clients;
        clients = tableClients.getSelectionModel().getSelectedItems();
        System.out.println(clients.get(0).getId());
    }

    public void onDeleteButtonClick(ActionEvent actionEvent) throws SQLException {
        ObservableList<Client> clients;
        clients = tableClients.getSelectionModel().getSelectedItems();

        if (clients.isEmpty()) {
            return;
        }

        Client selectedClient = clients.get(0);
        clientService.delete(selectedClient.getId());

        clientsTableRefresh();
    }

    public void exitButtonOnClick(ActionEvent action) {
        /*
            Закрывает приложение
        */
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
