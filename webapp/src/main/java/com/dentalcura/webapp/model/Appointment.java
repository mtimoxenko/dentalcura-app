package com.dentalcura.webapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


}
