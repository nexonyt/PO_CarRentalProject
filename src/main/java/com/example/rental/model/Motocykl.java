package com.example.rental.model;

import com.example.rental.enums.FuelType;
import com.example.rental.enums.VehicleType;

public class Motocykl extends Vehicle {

    private FuelType fuelType;

    public Motocykl(String brand, String model, double pricePerDay, FuelType fuelType) {
        super(brand, VehicleType.MOTOCYKL, pricePerDay);
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return super.toString() + ", Paliwo: " + fuelType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}
