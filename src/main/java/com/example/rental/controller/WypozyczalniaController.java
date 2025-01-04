package com.example.rental.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import com.example.rental.model.Car;
import com.example.rental.model.Motocykl;
import com.example.rental.model.Vehicle;
import com.example.rental.model.Client;
import com.example.rental.enums.FuelType;
import java.util.ArrayList;
import java.util.List;

public class WypozyczalniaController {

    @FXML
    private Label label;

    @FXML
    private ListView<String> vehicleListView;

    @FXML
    private ListView<String> vehicleRentedListView;

    @FXML
    private Button rentCarButton;

    @FXML
    private Button rentBikeButton;

    @FXML
    private Label rentalStatusLabel;

    @FXML
    private Label clientDetailsLabel;

    private List<Vehicle> vehicles;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField driversLicenseTextField;


    @FXML
    public void initialize() {

        vehicles = new ArrayList<>();
        vehicles.add(new Car("Ford", "Mustang", 150.0, FuelType.DIESEL));
        vehicles.add(new Car("Audi", "A4", 130.0, FuelType.BENZYNA));
        vehicles.add(new Motocykl("Yamaha", "R1", 100.0, FuelType.DIESEL));
        vehicles.add(new Motocykl("Kawasaki", "Ninja", 90.0, FuelType.BENZYNA));


        List<String> vehicleDescriptions = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDescriptions.add(vehicle.toString());
        }
        vehicleListView.getItems().setAll(vehicleDescriptions);


        rentalStatusLabel.setText("Status wynajmu: Brak");
    }

    @FXML
    private void handleRentCar() {
        String selectedVehicle = vehicleListView.getSelectionModel().getSelectedItem();

        if (selectedVehicle != null) {
            if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() || ageTextField.getText().isEmpty() || driversLicenseTextField.getText().isEmpty()) {
            } else {
                try {

                    int age = Integer.parseInt(ageTextField.getText());

                    if (age < 18) {
                        rentalStatusLabel.setText("Klient musi mieć co najmniej 18 lat!");
                        return;
                    }

                    String name = nameTextField.getText();
                    String surname = surnameTextField.getText();
                    String driversLicenseNumber = driversLicenseTextField.getText();

                    if (driversLicenseNumber.length() <= 5) {
                        rentalStatusLabel.setText("Numer prawa jazdy jest za krótki. Wymagany minimum 6 cyfrowy!");
                        return;
                    }

                    Client client = new Client(name, surname, age, driversLicenseNumber);

                    for (Vehicle vehicle : vehicles) {
                        if (vehicle.toString().equals(selectedVehicle)) {
                            vehicle.setClient(client);
                            break;
                        }
                    }

                    rentalStatusLabel.setText("Wynajęto pojazd: " + selectedVehicle + " przez " + client.getName() + " " + client.getSurname());

                    vehicleRentedListView.getItems().add(selectedVehicle);
                    vehicleListView.getItems().remove(selectedVehicle);

                } catch (NumberFormatException e) {
                    rentalStatusLabel.setText("Wiek musi być liczbą!");
                }
            }
        } else {
            rentalStatusLabel.setText("Wybierz pojazd do wynajmu.");
        }
    }

    @FXML
    private void handleGetRentedCarButton() {
        String selectedRentedVehicle = vehicleRentedListView.getSelectionModel().getSelectedItem();

        if (selectedRentedVehicle != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie odbioru pojazdu");
            alert.setHeaderText("Czy z pojazdem wszystko okej?");
            alert.setContentText("Kliknij 'OK', aby zakończyć wynajem lub 'Cancel' aby anulować.");


            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    vehicleListView.getItems().add(selectedRentedVehicle);
                    vehicleRentedListView.getItems().remove(selectedRentedVehicle);


                    rentalStatusLabel.setText("Pojazd " + selectedRentedVehicle + " został zwrócony.");
                } else {

                    rentalStatusLabel.setText("Anulowano odbiór pojazdu.");
                }
            });
        } else {
            rentalStatusLabel.setText("Wybierz pojazd do zwrotu.");
        }
    }

    @FXML
    private void handleRentedVehicleClicked() {
        String selectedRentedVehicle = vehicleRentedListView.getSelectionModel().getSelectedItem();

        if (selectedRentedVehicle != null) {

            for (Vehicle vehicle : vehicles) {
                if (vehicle.toString().equals(selectedRentedVehicle)) {
                    Client client = vehicle.getClient();
                    if (client != null) {
                        String clientDetails = "Imię: " + client.getName() + "\n" + "Nazwisko: " + client.getSurname() + "\n" + "Wiek: " + client.getAge() + "\n" + "Nr prawa jazdy: " + client.getDriversLicenseNumber();
                        clientDetailsLabel.setText(clientDetails);
                    } else {
                        clientDetailsLabel.setText("Brak danych klienta.");
                    }
                    break;
                }
            }
        } else {
            clientDetailsLabel.setText("Wybierz pojazd, aby zobaczyć dane klienta.");
        }
    }


}
