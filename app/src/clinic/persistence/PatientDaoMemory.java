package clinic.persistence;

import clinic.entities.Patient;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PatientDaoMemory implements IDao<Patient>{

    private final static Logger LOGGER = Logger.getLogger(PatientDaoMemory.class);
    private final List<Patient> patientList = new ArrayList<>();


    @Override
    public void createTable() {
    }

    @Override
    public Patient insert(Patient patient) {
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
        return null;
    }

    @Override
    public Patient updateByID(Patient patient) {
        return null;
    }

    @Override
    public Patient deleteByID(Long id) {
        return null;
    }

    @Override
    public String toString() {
        return "In-Memory";
    }
}
