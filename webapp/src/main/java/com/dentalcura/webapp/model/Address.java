package com.dentalcura.webapp.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street_name", nullable = false) // Especifica el nombre de la columna en la base de datos
    private String streetName;
    @Column(name = "street_num", nullable = false) // Especifica el nombre de la columna en la base de datos
    private Integer streetNumber;
    private Integer floor;
    private String department;

    @OneToOne
    @JoinColumn(name = "patient_id") // Nombre de la columna de clave foránea en la tabla Direccion
    private Patient patient;


}
