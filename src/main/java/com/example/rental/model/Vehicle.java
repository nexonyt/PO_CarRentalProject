package com.example.rental.model;



public abstract class Vehicle {
    private String brand;       // Marka pojazdu
    private String type;        // Typ pojazdu
    private double pricePerDay; // Cena za dzień wynajmu
    private static int nextId = 1;  // Automatyczny inkrementator ID
    private int id;
    private Client client;      // Klient wynajmujący pojazd

    public Vehicle(String brand, String type, double pricePerDay) {
        this.id = nextId++;
        this.brand = brand;
        this.type = type;
        this.pricePerDay = pricePerDay;
        this.client = null;
    }

    // Getters i Setters
    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String toString() {
        return "Marka: " + brand + ", Typ: " + type + ", Cena: " + pricePerDay + " PLN/dzień";
    }
}
