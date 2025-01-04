package com.example.rental.controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
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
    private Label vehicleInfoTextArea;

    public void initialize() {
        vehicles = new ArrayList<>();
        vehicles.add(new Car("Ford", "Mustang", 150.0, FuelType.DIESEL));
        vehicles.add(new Car("Audi", "A4", 130.0, FuelType.BENZYNA));
        vehicles.add(new Motocykl("Yamaha", "R1", 100.0, FuelType.DIESEL));
        vehicles.add(new Motocykl("Kawasaki", "Ninja", 90.0, FuelType.BENZYNA));


        List<String> vehicleDescriptions = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDescriptions.add(vehicle.getSimpleDescription());
        }
        vehicleListView.getItems().setAll(vehicleDescriptions);

        rentalStatusLabel.setText("Status wynajmu: Brak");
    }


    public void showVehicleDetails() {
        String selectedVehicleDescription = vehicleListView.getSelectionModel().getSelectedItem();

        if (selectedVehicleDescription != null) {
            try {
                int selectedVehicleId = Integer.parseInt(selectedVehicleDescription.split(" ")[0].trim());

                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getId() == selectedVehicleId) {
                        vehicleInfoTextArea.setText(vehicle.getFullDescription());
                        return;
                    }
                }

                rentalStatusLabel.setText("Nie znaleziono pojazdu o ID: " + selectedVehicleId);
            } catch (NumberFormatException e) {
                rentalStatusLabel.setText("Błąd parsowania ID pojazdu: " + selectedVehicleDescription);
            }
        } else {
            rentalStatusLabel.setText("Wybierz pojazd, aby zobaczyć szczegóły.");
        }
    }


    @FXML
    private void handleRentCar() {
        String selectedVehicleDescription = vehicleListView.getSelectionModel().getSelectedItem();

        if (selectedVehicleDescription != null) {
            if (nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty() ||
                    ageTextField.getText().isEmpty() || driversLicenseTextField.getText().isEmpty()) {

                rentalStatusLabel.setText("Wszystkie pola muszą być wypełnione!");
            } else {
                try {
                    int age = Integer.parseInt(ageTextField.getText());

                    if (age < 18) {
                        rentalStatusLabel.setText("Klient musi mieć co najmniej 18 lat!");
                        return;
                    }

                    String name = nameTextField.getText();
                    String surname = nameTextField.getText();
                    String driversLicenseNumber = driversLicenseTextField.getText();

                    if (driversLicenseNumber.length() < 6) {
                        rentalStatusLabel.setText("Numer prawa jazdy jest za krótki. Wymagany minimum 6 cyfrowy!");
                        return;
                    }

                    Client client = new Client(name, surname, age, driversLicenseNumber, false);

                    int selectedVehicleId = Integer.parseInt(selectedVehicleDescription.split(" ")[0].trim());

                    for (Vehicle vehicle : vehicles) {
                        if (vehicle.getClient() != null && vehicle.getClient().getDriversLicenseNumber().equals(client.getDriversLicenseNumber())) {
                            if (vehicle.getClient().getBlocked()) {
                                rentalStatusLabel.setText("Ten klient jest zablokowany i nie może wypożyczyć pojazdu.");
                                return;
                            }
                        }
                    }

                    for (Vehicle vehicle : vehicles) {
                        if (vehicle.getId() == selectedVehicleId) {
                            vehicle.setClient(client);
                            System.out.println("Klient przypisany do pojazdu: " + vehicle.getClient());
                            break;
                        }
                    }

                    rentalStatusLabel.setText("Wynajęto pojazd: " + selectedVehicleDescription + " przez " + client.getName() + " " + client.getSurname());
                    vehicleRentedListView.getItems().add(selectedVehicleDescription);
                    vehicleListView.getItems().remove(selectedVehicleDescription);

                } catch (NumberFormatException e) {
                    rentalStatusLabel.setText("Wiek musi być liczbą!");
                }
            }
        } else {
            rentalStatusLabel.setText("Wybierz pojazd do wynajmu.");
        }
    }



    @FXML
    private void handleBlockClient() {
        String selectedVehicleDescription = vehicleRentedListView.getSelectionModel().getSelectedItem();

        if (selectedVehicleDescription != null) {
            try {
                int selectedVehicleId = Integer.parseInt(selectedVehicleDescription.split(" ")[0].trim());
                System.out.println(selectedVehicleId);
                Vehicle selectedVehicle = null;
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getId() == selectedVehicleId) {
                        selectedVehicle = vehicle;
                        break;
                    }
                }

                if (selectedVehicle == null) {
                    rentalStatusLabel.setText("Nie znaleziono pojazdu o ID: " + selectedVehicleId);
                    return;
                }

                if (selectedVehicle.getClient() != null) {

                    selectedVehicle.getClient().setBlocked(true);
                    rentalStatusLabel.setText("Klient " + selectedVehicle.getClient().getName() + " został zablokowany.");
                } else {
                    rentalStatusLabel.setText("Nie można zablokować klienta: brak powiązanego klienta.");
                }
            } catch (NumberFormatException e) {
                rentalStatusLabel.setText("Błąd parsowania ID pojazdu.");
            }
        } else {
            rentalStatusLabel.setText("Wybierz pojazd, aby zablokować klienta.");
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
            try {
                int vehicleId = Integer.parseInt(selectedRentedVehicle.split(" ")[0]);

                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getId() == vehicleId) {
                        Client client = vehicle.getClient();
                        if (client != null) {
                            String clientDetails = "Imię: " + client.getName() + "\n" +
                                    "Nazwisko: " + client.getSurname() + "\n" +
                                    "Wiek: " + client.getAge() + "\n" +
                                    "Nr prawa jazdy: " + client.getDriversLicenseNumber();
                            clientDetailsLabel.setText(clientDetails);
                        } else {
                            clientDetailsLabel.setText("Brak danych klienta.");
                        }
                        return;
                    }
                }


                clientDetailsLabel.setText("Nie znaleziono pojazdu o podanym ID.");
            } catch (NumberFormatException e) {
                clientDetailsLabel.setText("Nieprawidłowy format ID pojazdu.");
            }
        } else {
            clientDetailsLabel.setText("Wybierz pojazd, aby zobaczyć dane klienta.");
        }
    }

}