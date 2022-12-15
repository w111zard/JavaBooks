package com.example.javabooks.entities;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private Float price;

    public Book(int id, String author, String title, String genre, Float price) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() {
        return title;
    }
    public void setAuthor(String author) { this.author = author; }
    public String getAuthor() {
        return author;
    }
    public void setGenre(String genre) { this.genre = genre; }
    public String getGenre() {
        return genre;
    }
    public void setPrice(Float price) { this.price = price; }
    public Float getPrice() { return price; }
}
