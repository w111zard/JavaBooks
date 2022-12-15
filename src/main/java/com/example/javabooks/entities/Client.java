package com.example.javabooks.entities;

public class Client extends Person {
    private String address;

    public Client(Integer id, String lastName, String firstName, String secondName,
                  String email, String address) {
        super(id, lastName, firstName, secondName, email);
        this.address = address;
    }

    public void setAddress(String address) { this.address = address; }

    public String getAddress() { return address; }
}
