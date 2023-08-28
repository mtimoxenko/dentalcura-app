package com.dentalcura.webapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Appointment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    @ManyToOne
    @JoinColumn(name = "patient_id") // Nombre de la columna de clave foránea en la tabla Appointment
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "dentist_id") // Nombre de la columna de clave foránea en la tabla Appointment
    private Dentist dentist;




//    @Override
//    public String toString() {
//        return "Appointment: " + date +
//                ", Dentist: " + dentist.name() + " " + dentist.surname() +
//                ", Patient: " + patient.name() + " " + patient.surname();
//    }
}
