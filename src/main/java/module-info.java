module com.example.javabooks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javabooks to javafx.fxml;
    exports com.example.javabooks;
    exports com.example.javabooks.entities;
    opens com.example.javabooks.entities to javafx.fxml;
    exports com.example.javabooks.controllers;
    opens com.example.javabooks.controllers to javafx.fxml;
}