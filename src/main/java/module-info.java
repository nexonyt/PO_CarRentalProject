module com.example.rental {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.rental.controller to javafx.fxml;
    exports com.example.rental;
    exports com.example.rental.controller;
    exports com.example.rental.model;
}
