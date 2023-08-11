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
        LOGGER.info("Data successfully inserted in memory: " + patient.name() + " " + patient.surname());
        return patient;
    }

    @Override
    public List<Patient> selectAll() {
        patientList.forEach(LOGGER::info);
        LOGGER.info("Retrieving data from the memory...");
        return patientList;
    }
}
