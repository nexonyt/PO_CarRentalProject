package com.example.rental.model;

public class Client {
    private String name;
    private String surname;
    private int age;
    private String driversLicenseNumber;

    public Client(String name, String surname, int age, String driversLicenseNumber) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.driversLicenseNumber = driversLicenseNumber;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }
}
