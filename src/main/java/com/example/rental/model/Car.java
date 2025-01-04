package com.example.rental.model;
import com.example.rental.model.Vehicle;
import com.example.rental.enums.VehicleType;
import com.example.rental.enums.FuelType;

public class Car extends Vehicle {
    private FuelType fuelType;

    public Car(String brand, String model, double pricePerDay, FuelType fuelType) {
        super(brand, model,VehicleType.AUTO, pricePerDay);
        this.fuelType = fuelType;
    }

    @Override
    public String getFullDescription() {
        return super.getFullDescription() + "\nPaliwo: " + fuelType;
    }

    @Override
    public String getSimpleDescription() {
        return super.getSimpleDescription();
    }

    // Getters and Setters
    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return super.toString() + ", Paliwo: " + fuelType;
    }
}