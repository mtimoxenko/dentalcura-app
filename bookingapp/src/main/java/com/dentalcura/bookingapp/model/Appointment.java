package com.dentalcura.bookingapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Appointment{

       @Getter
       private final Long id;
       private final String date;
       private final Patient patient;
       private final Dentist dentist;


    @Override
    public String toString() {
        return "Appointment: " + date +
                ", Dentist: " + dentist.name() + " " + dentist.surname() +
                ", Patient: " + patient.name() + " " + patient.surname();
    }
}
