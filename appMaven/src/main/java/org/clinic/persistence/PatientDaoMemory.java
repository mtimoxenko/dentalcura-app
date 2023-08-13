package org.clinic.persistence;

import org.clinic.entities.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PatientDaoMemory implements IDao<Patient>{

    private final static Logger LOGGER = Logger.getLogger(String.valueOf(PatientDaoMemory.class));
    private final List<Patient> patientList = new ArrayList<>();


    @Override
    public void createTable() {
    }

    @Override
    public Patient insert(Patient patient) {
        patientList.add(patient);
        LOGGER.info("Data saved in memory: " + patient.getName() + " " + patient.getSurname());
        return patient;
    }

    @Override
    public List<Patient> selectAll() {
        LOGGER.info("Retrieving data from memory...");
        patientList.forEach(patient -> LOGGER.info(patient.toString()));
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
