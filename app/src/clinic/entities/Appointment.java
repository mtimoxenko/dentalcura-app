package clinic.entities;

public class Appointment{

       private final Long id;
       private final String date;
       private final Patient patient;
       private final Dentist dentist;

    public Appointment(Long id, String date, Patient patient, Dentist dentist) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.dentist = dentist;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Patient getPatient() {
        return patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    @Override
    public String toString() {
        return "Appointment: " + date +
                ", Dentist: " + dentist.name() + " " + dentist.surname() +
                ", Patient: " + patient.name() + " " + patient.surname();
    }
}
