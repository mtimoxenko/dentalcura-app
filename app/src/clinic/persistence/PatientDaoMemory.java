package clinic.persistence;

import clinic.entities.Patient;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientDaoMemory implements IDao<Patient>{

    private final static Logger LOGGER = Logger.getLogger(PatientDaoMemory.class);
    private final List<Patient> patientList = new ArrayList<>();


    @Override
    public void createTable() {
    }

    @Override
    public Patient insert(Patient patient) {
        for (Patient patient1 : patientList) {
            if (Objects.equals(patient.id(), patient1.id())){
                LOGGER.error("error, not added, id repeated");
                return null;
            }
        }
        patientList.add(patient);
        LOGGER.info("Data saved in memory: " + patient.name() + " " + patient.surname());
        return patient;
    }

    @Override
    public List<Patient> selectAll() {
        LOGGER.info("Retrieving data from memory...");
        patientList.forEach(LOGGER::info);
        return patientList;
    }

    @Override
    public Patient selectByID(Long id) {
        Patient patientSelected = null;

        for (Patient patient : patientList) {
            if(Objects.equals(id, patient.id())){
                patientSelected = patient;
                LOGGER.info("Patient selected by ID: " + id + ". " + patient);
            }
        }
        return patientSelected;
    }

    @Override
    public Patient updateByID(Patient patient) {
        for (Patient patient1 : patientList) {
            if (Objects.equals(patient.id(), patient1.id())){
                patientList.add(patient);
                patientList.remove(patient1);
                LOGGER.info("Patient ID: " + patient.id() + ", successfully updated. " + patient1);
                return patient;
            }
        }
        LOGGER.error("error, id not found, patient not updated");
        return null;
    }

    @Override
    public Patient deleteByID(Long id) {

        for (Patient patient : patientList) {
            if (Objects.equals(id, patient.id())){
                patientList.remove(patient);
                LOGGER.info("Patient deleted by ID: " + id + ". " + patient);
                return patient;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "In-Memory";
    }
}
