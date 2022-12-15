package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import com.example.javabooks.entities.Client;
import com.example.javabooks.services.ClientService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.javabooks.libs.Settings;

public class ClientController implements Initializable {
    private SceneController sceneController;
    private ClientService clientService;

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> columnAddress;

    @FXML
    private TableColumn<Client, String> columnEmail;

    @FXML
    private TableColumn<Client, String> columnFirstName;

    @FXML
    private TableColumn<Client, Integer> columnId;

    @FXML
    private TableColumn<Client, String> columnLastName;

    @FXML
    private TableColumn<Client, String> columnSecondName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneController = new SceneController();
        clientService = new ClientService();

        // Инициализируем столбцы таблицы
        columnId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<Client, String>("lastName"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<Client, String>("firstName"));
        columnSecondName.setCellValueFactory(new PropertyValueFactory<Client, String>("secondName"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));

        // Загружем данные в таблицу
        loadData();
    }

    public void loadData() {
        /*
            Метод для загрузки данных в таблицу
            Также может использоваться для обновления данных в таблице
         */

        clientTable.setItems(clientService.findAll());

    }

    @FXML
    void onButtonAddClick(ActionEvent event) {
        Session.setClient(null);
        sceneController.openNewScene(event, "client_card.fxml", 600, 600);
    }

    @FXML
    void onButtonDeleteClick(ActionEvent event) {
        // Получаем выбранную строку
        ObservableList<Client> clients = clientTable.getSelectionModel().getSelectedItems();

        // Если пользователь не выбрал строку
        if (clients.isEmpty()) {
            return;
        }

        // Удаляем строку из таблицы
        Client selectedClient = clients.get(0);
        clientService.delete(selectedClient.getId());
    }

    @FXML
    void onButtonEditClick(ActionEvent event) {
        // Получаем выбранную строку
        ObservableList<Client> clients = clientTable.getSelectionModel().getSelectedItems();

        // Если пользователь не выбрал строку
        if (clients.isEmpty()) {
            return;
        }

        // Необходимо для того, чтобы передать другому окну данные о выбранном клиенте
        Client selectedClient = clients.get(0);
        Session.setClient(selectedClient);

        // Открываем новое окно
        sceneController.openNewScene(event, "client_card.fxml", Settings.CARD_WINDOW_W, Settings.CARD_WINDOW_H);
    }

    @FXML
    void onButtonRefreshClick(ActionEvent event) {
        loadData(); // Обновляем данные в таблице
    }

    @FXML
    void onButtonBookClick(ActionEvent event) {
        sceneController.switchToScene(event, "book.fxml", Settings.MAIN_WINDOW_W, Settings.MAIN_WINDOW_H);
    }

    @FXML
    void onButtonClientClick(ActionEvent event) {
        // Ничего не делаем, т.к мы уже находимся в этом окне
    }

    @FXML
    void onButtonProfileClick(ActionEvent event) {
        sceneController.switchToScene(event, "profile.fxml", Settings.MAIN_WINDOW_W, Settings.MAIN_WINDOW_H);
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
