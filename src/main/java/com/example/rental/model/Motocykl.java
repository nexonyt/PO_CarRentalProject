package com.example.rental.model;
import com.example.rental.model.Vehicle;
import com.example.rental.enums.FuelType;
import com.example.rental.enums.VehicleType;

public class Motocykl extends Vehicle {

    private FuelType fuelType;

    public Motocykl(String brand, String model, double pricePerDay, FuelType fuelType) {
        super(brand,model, VehicleType.MOTOCYKL, pricePerDay);
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