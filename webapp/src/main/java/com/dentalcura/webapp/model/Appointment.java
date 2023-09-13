package com.dentalcura.webapp.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "patient_id") // Nombre de la columna de clave foránea en la tabla Appointment
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "dentist_id") // Nombre de la columna de clave foránea en la tabla Appointment
    private Dentist dentist;


}
