package com.example.javabooks;

import com.example.javabooks.entities.Book;
import com.example.javabooks.entities.Cashier;
import com.example.javabooks.entities.Client;
import com.example.javabooks.entities.Order;

public class Session {
    private static Cashier cashier;

    private static Client client;

    private static Book book;

    private static Order order;

    private static String errorMessage;

    public static void setCashier(Cashier _cashier) {
        cashier = _cashier;
    }

    public static Cashier getCashier() {
        return cashier;
    }

    public static void setClient(Client _client) { client = _client; }

    public static Client getClient() { return client; }

    public static void setBook(Book _book) { book = _book; }

    public static Book getBook() { return book; }

    public static void setOrder(Order _order) { order = _order; }

    public static Order getOrder() { return order; }

    public static void setErrorMessage(String _errorMessage) {errorMessage = _errorMessage; }

    public static String getErrorMessage() { return errorMessage; }
}
