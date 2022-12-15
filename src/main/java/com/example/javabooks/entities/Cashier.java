package com.example.javabooks.entities;

public class Cashier extends Person {
    private String password;

    public Cashier(Integer id, String lastName, String firstName, String secondName,
                   String email, String password) {
        super(id, lastName, firstName, secondName, email);
        this.password = password;
    }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return password; }
}