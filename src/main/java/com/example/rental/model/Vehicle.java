package com.example.rental.model;

import com.example.rental.enums.VehicleType;

public abstract class Vehicle {
    private String brand;          // Marka pojazdu
    private VehicleType type;      // Typ pojazdu (CAR, MOTORCYCLE) - zmieniono z String na VehicleType
    private double pricePerDay;    // Cena za dzień wynajmu
    private static int nextId = 1; // Automatyczny inkrementator ID
    private int id;                // Unikalne ID pojazdu
    private Client client;         // Klient wynajmujący pojazd
    private String model;

    public Vehicle(String brand, String model,VehicleType type, double pricePerDay) {
        this.id = nextId++;
        this.model = model;
        this.brand = brand;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.client = null;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public VehicleType getVehicleType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setClient(Client client) {
        this.client = client;

    }


    public String getFullDescription() {
        return "ID: " + id + "\nMarka: " + brand + "\nModel: "+model+"\nTyp: " + type +
                "\nCena za dzień: " + pricePerDay + " PLN/dzień\n";
    }

    public String getSimpleDescription() {
        return id + " " + brand + " " + model;
    }

    @Override
    public String toString() {
        return "Marka: " + brand + ", Typ: " + type + ", Cena: " + pricePerDay + " PLN/dzień";
    }
}