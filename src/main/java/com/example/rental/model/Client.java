package com.example.rental.model;

public class Client {
    private String name;
    private String surname;
    private int age;
    private String driversLicenseNumber;
    private boolean blocked;

    public Client(String name, String surname, int age, String driversLicenseNumber, boolean blocked) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.driversLicenseNumber = driversLicenseNumber;
        this.blocked = blocked;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
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
