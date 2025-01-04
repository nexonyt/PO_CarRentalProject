package com.example.rental;

import java.util.ArrayList;
import java.util.List;
import com.example.rental.model.Car;

public class Wypozyczalnia {
    private List<Car> pojazdy;

    public Wypozyczalnia() {
        this.pojazdy = new ArrayList<>();
    }

    public void dodajSamochod(Car samochod) {
        pojazdy.add(samochod);
    }

    public List<Car> getPojazdy() {
        return pojazdy;
    }
}
