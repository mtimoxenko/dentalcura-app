package com.dentalcura.bookingapp.model;

public record Appointment(int id, String date, Patient patient, Dentist dentist) {

    @Override
    public String toString() {
        return "Appointment: " + date +
                ", Dentist: " + dentist.name() + " " + dentist.surname() +
                ", Patient: " + patient.name() + " " + patient.surname();
    }
}
