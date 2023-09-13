package com.dentalcura.webapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(name = "ni_number", nullable = false) // Especifica el nombre de la columna en la base de datos
    private Integer niNumber;
    @Column(name = "reg_date") // Especifica el nombre de la columna en la base de datos
    private String registrationDate;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;


}
