package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import com.example.javabooks.entities.Book;
import com.example.javabooks.entities.Client;
import com.example.javabooks.entities.Order;
import com.example.javabooks.libs.Validator;
import com.example.javabooks.services.BookService;
import com.example.javabooks.services.ClientService;
import com.example.javabooks.services.OrderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class OrderCardController implements Initializable {
    @FXML
    private ComboBox<String> bookIdComboBox;

    @FXML
    private ComboBox<String> clientIdComboBox;


    private SceneController sceneController;
    private OrderService orderService;
    private BookService bookService;
    private ClientService clientService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneController = new SceneController();
        orderService = new OrderService();
        bookService = new BookService();
        clientService = new ClientService();

        bookIdComboBox.setItems(getBooks());
        clientIdComboBox.setItems(getClients());
    }

    private ObservableList<String> getBooks() {
        ObservableList<String> comboBoxItems = FXCollections.observableArrayList();

        ObservableList<Book> books = bookService.findAll();
        for (Book book : books) {
            String item = String.format("%s - \"%s\" [%d]", book.getAuthor(), book.getTitle(), book.getId());
            comboBoxItems.add(item);
        }

        return comboBoxItems;
    }

    private ObservableList<String> getClients() {
        ObservableList<String> comboBoxItems = FXCollections.observableArrayList();

        ObservableList<Client> clients = clientService.findAll();
        for (Client client : clients) {
            String item = String.format("%s %s %s [%d]", client.getLastName(), client.getFirstName(), client.getSecondName(), client.getId());
            comboBoxItems.add(item);
        }

        return comboBoxItems;
    }
    @FXML
    void OnAddButtonClick(ActionEvent event) {
        String bookIdString = bookIdComboBox.getValue();
        String clientIdString = clientIdComboBox.getValue();

        if (bookIdString == null || clientIdString == null) {
            return;
        }

        Integer bookIdInt = getValueFromString(bookIdString);
        Integer clientIdInt = getValueFromString(clientIdString);

        if (Session.getOrder() == null) {
            Order order = new Order(0, clientIdInt, Session.getCashier().getId(), bookIdInt, getCurrentDateTime());
            orderService.create(order);
        }
        else {
            Order order = new Order(Session.getOrder().getId(), clientIdInt,
                    Session.getCashier().getId(), bookIdInt, getCurrentDateTime());
            orderService.update(order);
        }

        sceneController.closeScene(event);
    }

    private String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private Integer getValueFromString(String input) {
        String extracted = input.substring(input.indexOf("[") + 1, input.indexOf("]"));
        return Integer.parseInt(extracted);
    }
    @FXML
    void onCancelButtonClick(ActionEvent event) {
        sceneController.closeScene(event);
    }
}
