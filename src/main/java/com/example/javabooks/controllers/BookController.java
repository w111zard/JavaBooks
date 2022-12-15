package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import com.example.javabooks.entities.Book;
import com.example.javabooks.entities.Client;
import com.example.javabooks.services.BookService;
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

public class BookController implements Initializable {
    private SceneController sceneController;
    private BookService bookService;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> columnAuthor;

    @FXML
    private TableColumn<Book, String> columnGenre;

    @FXML
    private TableColumn<Book, Integer> columnId;

    @FXML
    private TableColumn<Book, Float> columnPrice;

    @FXML
    private TableColumn<Book, String> columnTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneController = new SceneController();
        bookService = new BookService();

        // Инициализируем столбцы таблицы
        columnId.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        columnGenre.setCellValueFactory(new PropertyValueFactory<Book, String>("genre"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Book, Float>("price"));

        // Загружем данные в таблицу
        loadData();
    }

    public void loadData() {
        /*
            Метод для загрузки данных в таблицу
            Также может использоваться для обновления данных в таблице
         */

        bookTable.setItems(bookService.findAll());
    }

    @FXML
    void onButtonAddClick(ActionEvent event) {
        Session.setBook(null);
        sceneController.openNewScene(event, "book_card.fxml", 600, 600);
    }

    @FXML
    void onButtonDeleteClick(ActionEvent event) {
        // Получаем выбранную строку
        ObservableList<Book> books = bookTable.getSelectionModel().getSelectedItems();

        // Если пользователь не выбрал строку
        if (books.isEmpty()) {
            return;
        }

        // Удаляем строку из таблицы
        Book selectedBook = books.get(0);
        bookService.delete(selectedBook.getId());
    }

    @FXML
    void onButtonEditClick(ActionEvent event) {
        // Получаем выбранную строку
        ObservableList<Book> books = bookTable.getSelectionModel().getSelectedItems();

        // Если пользователь не выбрал строку
        if (books.isEmpty()) {
            return;
        }

        // Необходимо для того, чтобы передать другому окну данные о выбранной книге
        Book selectedBook = books.get(0);
        Session.setBook(selectedBook);

        // Открываем новое окно
        sceneController.openNewScene(event, "book_card.fxml", Settings.CARD_WINDOW_W, Settings.CARD_WINDOW_H);
    }

    @FXML
    void onButtonRefreshClick(ActionEvent event) {
        loadData(); // Обновляем данные в таблице
    }

    @FXML
    void onButtonBookClick(ActionEvent event) {
        // Ничего не делаем, т.к мы уже находимся в этом окне
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
        sceneController.switchToScene(event, "order.fxml", Settings.MAIN_WINDOW_W, Settings.MAIN_WINDOW_H);
    }

    @FXML
    void onButtonExitClick(ActionEvent event) {
        sceneController.closeScene(event); // Закрываем окно
    }
}
