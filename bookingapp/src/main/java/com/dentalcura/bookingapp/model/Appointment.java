package com.dentalcura.bookingapp.model;

<<<<<<< HEAD
public record Appointment(int id, String date, Patient patient, Dentist dentist) {

=======
public record Appointment(
        Long id,
        String date,
        Patient patient,
        Dentist dentist
) {
>>>>>>> 74ad6c0d65a3d7f0c6bbdcd1cbdf13f05bded0e5
    @Override
    public String toString() {
        return "Appointment: " + date +
                ", Dentist: " + dentist.name() + " " + dentist.surname() +
                ", Patient: " + patient.name() + " " + patient.surname();
    }
}
