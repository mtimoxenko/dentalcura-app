package com.dentalcura.webapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String surname;
    @Column(name = "ni_number") // Especifica el nombre de la columna en la base de datos
    private Integer niNumber;
    @Column(name = "reg_date") // Especifica el nombre de la columna en la base de datos
    private String registrationDate;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

//    @Override
//    public String toString() {
//        return "Patient: " + name + " " + surname +
//                ", ni_number: " + niNumber;
//    }
}
