package com.example.rental.model;
import com.example.rental.enums.FuelType;
import com.example.rental.enums.VehicleType;

public class Motorbike extends Vehicle {

    private FuelType fuelType;

    public Motorbike(String brand, String model, double pricePerDay, FuelType fuelType) {
        super(brand,model, VehicleType.MOTORBIKE, pricePerDay);
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return super.toString() + ", Paliwo: " + fuelType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    @Override
    public String getFullDescription() {
        return super.getFullDescription() + "\nPaliwo: " + fuelType;
    }

    @Override
    public String getSimpleDescription() {
        return super.getSimpleDescription();
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}