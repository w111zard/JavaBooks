package com.example.javabooks.entities;

public abstract class Person {
    private Integer id;
    private String lastName;
    private String firstName;
    private String secondName;
    private String email;

    public Person(Integer id, String lastName, String fistName,
                  String secondName, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = fistName;
        this.secondName = secondName;
        this.email = email;
    }

    public Integer getId() { return id; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getLastName() { return lastName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getFirstName() { return firstName; }

    public void setSecondName(String secondName) { this.secondName = secondName; }

    public String getSecondName() { return  secondName; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }
}
