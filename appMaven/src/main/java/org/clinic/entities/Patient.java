package org.clinic.entities;

public class Patient{
    private final Long id;
    private final String name;
    private final String surname;
    private final String address;
    private final int niNumber;
    private final String registrationDate;

    public Patient(Long id, String name, String surname, String address, int niNumber, String registrationDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.niNumber = niNumber;
        this.registrationDate = registrationDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public int getNiNumber() {
        return niNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return "Patient: " + name + " " + surname +
                ", ni_number: " + niNumber;
    }
}
