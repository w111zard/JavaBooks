module com.example.javabooks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javabooks to javafx.fxml;
    exports com.example.javabooks;
}