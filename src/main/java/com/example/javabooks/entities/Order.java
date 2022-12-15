package com.example.javabooks.entities;

public class Order {
    private Integer id;
    private Integer clientId;
    private Integer cashierId;
    private Integer bookId;
    private String date;

    public Order(Integer id, Integer clientId, Integer cashierIdId, Integer bookId, String date) {
        this.id = id;
        this.clientId = clientId;
        this.cashierId = cashierIdId;
        this.bookId = bookId;
        this.date = date;
    }
    public Integer getId() { return id; }

    public void setClientId(Integer clientId) { this.clientId = clientId; }
    public Integer getClientId() { return clientId; }
    public void setCashierId(Integer cashierId) { this.cashierId = cashierId; }
    public Integer getCashierId() { return this.cashierId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
    public Integer getBookId() { return bookId; }
    public void setDate(String date) { this.date = date; }
    public String getDate() { return date; }
}
