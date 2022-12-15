package com.example.javabooks.controllers;

import com.example.javabooks.SceneController;
import com.example.javabooks.Session;
import com.example.javabooks.entities.Book;
import com.example.javabooks.entities.Client;
import com.example.javabooks.libs.Validator;
import com.example.javabooks.services.BookService;
import com.example.javabooks.services.ClientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BookCardController implements Initializable {
    private SceneController sceneController;
    private BookService bookService;
    @FXML
    private Label authorErrorLabel;

    @FXML
    private TextField authorField;

    @FXML
    private Label genreErrorLabel;

    @FXML
    private TextField genreField;

    @FXML
    private Label priceErrorField;

    @FXML
    private TextField priceField;

    @FXML
    private Label titleErrorLabel;

    @FXML
    private TextField titleField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneController = new SceneController();
        bookService = new BookService();

        Book book = Session.getBook();
        if (book != null) {
            authorField.setText(book.getAuthor());
            titleField.setText(book.getTitle());
            genreField.setText(book.getGenre());
            priceField.setText(book.getPrice().toString());
        }
    }

    @FXML
    void OnAddButtonClick(ActionEvent event) {
        String author = authorField.getText();
        String title = titleField.getText();
        String genre = genreField.getText();
        String price = priceField.getText();

        if (! checkFields(author, title, genre, price)) {
            return;
        }

        if (Session.getBook() == null) {
            Book book = new Book(0, author, title, genre, Float.parseFloat(price));
            bookService.create(book);
        }
        else {
            Book book = new Book(Session.getBook().getId(), author, title, genre, Float.parseFloat(price));
            bookService.update(book);
        }

        sceneController.closeScene(event);
    }

    private boolean checkFields(String author, String title, String genre, String price) {

        String errorDescription;
        Boolean isValid = true;

        errorDescription = Validator.checkText(author);
        if (errorDescription != null) {
            authorErrorLabel.setText(errorDescription);
            isValid = false;
        }

        errorDescription = Validator.checkText(title);
        if (errorDescription != null) {
            titleErrorLabel.setText(errorDescription);
            isValid = false;
        }

        errorDescription = Validator.checkText(genre);
        if (errorDescription != null) {
            genreErrorLabel.setText(errorDescription);
            isValid = false;
        }

        errorDescription = Validator.checkText(price);
        if (errorDescription != null) {
            priceErrorField.setText(errorDescription);
            isValid = false;
        }

        return isValid;
    }
    @FXML
    void onCancelButtonClick(ActionEvent event) {
        sceneController.closeScene(event);
    }
}
