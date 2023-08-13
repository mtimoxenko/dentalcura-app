package org.clinic.entities;

public class Dentist{
    private final Long id;
    private final String name;
    private final String surname;
    private final int licenseNumber;

    public Dentist(Long id, String name, String surname, int licenseNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.licenseNumber = licenseNumber;
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

    public int getLicenseNumber() {
        return licenseNumber;
    }

    @Override
        public String toString() {
            return "Dentist: " + name + " " + surname +
                    ", License no.: " + licenseNumber;
        }
}


