package clinic.entities;

public record Dentist(Long id, String name, String surname, int licenseNumber) {

    @Override
    public String toString() {
        return "Dentist: " + name + " " + surname +
                ", License no.: " + licenseNumber;
    }
}
