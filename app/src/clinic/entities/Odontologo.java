package clinic.entities;

public record Odontologo(Long id, String nombre, String apellido, int numeroMatricula) {

    @Override
    public String toString() {
        return "Odontologo: " + nombre + " " + apellido +
                ", Matricula: " + numeroMatricula;
    }
}
