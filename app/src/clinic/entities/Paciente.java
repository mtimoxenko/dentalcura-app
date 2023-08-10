package clinic.entities;

import java.sql.Date;
import java.time.LocalDate;


public record Paciente(Long id, String nombre, String apellido, String domicilio, int dni, String fechaAlta) {

    @Override
    public String toString() {
        return "Paciente: " + nombre + " " + apellido +
                ", dni: " + dni;
    }
}
