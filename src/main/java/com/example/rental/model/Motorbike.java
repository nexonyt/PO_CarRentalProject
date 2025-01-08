package com.example.rental.model;
import com.example.rental.enums.FuelType;
import com.example.rental.enums.VehicleType;

public class Motorbike extends Vehicle {

    private FuelType fuelType;
    public int vehicleMileage;

    public Motorbike(String brand, String model, double pricePerDay, FuelType fuelType, int vehicleMileage) {
        super(brand,model, VehicleType.MOTOCYKL, pricePerDay,vehicleMileage);
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return super.toString() + "\nPaliwo: " + fuelType;
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