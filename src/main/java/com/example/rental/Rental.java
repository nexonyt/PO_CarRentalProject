package com.example.rental;

import java.util.ArrayList;
import java.util.List;
import com.example.rental.model.Car;

public class Rental {
    private List<Car> vehicles;

    public Rental() {
        this.vehicles = new ArrayList<>();
    }

    public void addCar(Car samochod) {
        vehicles.add(samochod);
    }

    public List<Car> getVehicles() {
        return vehicles;
    }
}
