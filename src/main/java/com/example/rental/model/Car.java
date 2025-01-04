package com.example.rental.model;

public class Car extends Vehicle {

    public Car(String brand, String model, double pricePerDay, String engineType) {
        super(brand, model, pricePerDay);
    }


    public String getType() {
        return "Car";
    }
}
