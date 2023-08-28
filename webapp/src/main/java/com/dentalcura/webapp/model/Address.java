package com.dentalcura.webapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streetName;
    private Integer streetNumber;
    private Integer floor;
    private String department;

    @OneToOne
    @JoinColumn(name = "patient_id") // Nombre de la columna de clave foránea en la tabla Direccion
    private Patient patient;


//    @Override
//    public String toString() {
//        return "Address: " + streetName +
//                " " + streetNumber + ", " + floor +
//                "-" + department;
//    }

}
