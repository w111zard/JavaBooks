package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import com.example.javabooks.entities.Book;
import com.example.javabooks.entities.Order;
import com.example.javabooks.libs.Settings;
import com.example.javabooks.services.OrderService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private TableColumn<Order, Integer> columnBookId;

    @FXML
    private TableColumn<Order, Integer> columnCashierId;

    @FXML
    private TableColumn<Order, Integer> columnClientId;

    @FXML
    private TableColumn<Order, String> columnDate;

    @FXML
    private TableColumn<Order, Integer> columnId;

    @FXML
    private TableView<Order> orderTable;

    private SceneController sceneController;
    private OrderService orderService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneController = new SceneController();
        orderService = new OrderService();

        System.out.println("here");

        // Инициализируем столбцы таблицы
        columnId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
        columnClientId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("clientId"));
        columnCashierId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("cashierId"));
        columnBookId.setCellValueFactory(new PropertyValueFactory<Order, Integer>("bookId"));
        columnDate.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));

        // Загружем данные в таблицу
        loadData();
    }

    public void loadData() {
        /*
            Метод для загрузки данных в таблицу
            Также может использоваться для обновления данных в таблице
         */
        try {
            orderTable.setItems(orderService.findAll());
        } catch (java.sql.SQLException exception) {
            System.out.println(exception);
        }
    }

    @FXML
    void onButtonAddClick(ActionEvent event) {
        Session.setOrder(null);
        sceneController.openNewScene(event, "order_card.fxml", Settings.CARD_WINDOW_W, Settings.CARD_WINDOW_H);
    }

    @FXML
    void onButtonDeleteClick(ActionEvent event) {
        // Получаем выбранную строку
        ObservableList<Order> orders = orderTable.getSelectionModel().getSelectedItems();

        // Если пользователь не выбрал строку
        if (orders.isEmpty()) {
            return;
        }

        // Удаляем строку из таблицы
        Order selectedOrder = orders.get(0);
        orderService.delete(selectedOrder.getId());
    }

    @FXML
    void onButtonEditClick(ActionEvent event) {
        // Получаем выбранную строку
        ObservableList<Order> orders = orderTable.getSelectionModel().getSelectedItems();

        // Если пользователь не выбрал строку
        if (orders.isEmpty()) {
            return;
        }

        // Необходимо для того, чтобы передать другому окну данные о выбранном заказе
        Order selectedOrder = orders.get(0);
        Session.setOrder(selectedOrder);

        // Открываем новое окно
        sceneController.openNewScene(event, "order_card.fxml", Settings.CARD_WINDOW_W, Settings.CARD_WINDOW_H);
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
        sceneController.switchToScene(event, "client.fxml", Settings.MAIN_WINDOW_W, Settings.MAIN_WINDOW_H);
    }

    @FXML
    void onButtonProfileClick(ActionEvent event) {
        sceneController.switchToScene(event, "profile.fxml", Settings.MAIN_WINDOW_W, Settings.MAIN_WINDOW_H);
    }

    @FXML
    void onButtonOrderClick(ActionEvent event) {
        // Ничего не делаем, т.к нужное окно уже открыто
    }

    @FXML
    void onButtonExitClick(ActionEvent event) {
        sceneController.closeScene(event); // Закрываем окно
    }

}
