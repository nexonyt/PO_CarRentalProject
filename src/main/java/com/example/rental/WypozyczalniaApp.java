package com.example.rental;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class WypozyczalniaApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/wypozyczalnia/mainApp.fxml"));
        AnchorPane root = loader.load();

        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("Centrum zarządzania wypożyczalnią pojazdów (samochodów i motocykli)");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
