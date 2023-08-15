package com.dentalcura.bookingapp.dao.impl;


import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Patient;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


@Slf4j
public class PatientDAOMemory implements IDao<Patient> {
    private final List<Patient> patientList = new ArrayList<>();


    @Override
    public void createTable() {
    }

    @Override
    public Patient insert(Patient patient) {
        for (Patient patient1 : patientList) {
            if (Objects.equals(patient.id(), patient1.id())){
                log.error("error, not added, id repeated");
                return null;
            }
        }
        patientList.add(patient);
        log.info("Data saved in memory: " + patient.name() + " " + patient.surname());
        return patient;
    }

    @Override
    public List<Patient> selectAll() {
        log.info("Retrieving data from memory...");
        patientList.forEach(patient -> log.info("Patient: " + patient));
        return patientList;
    }

    @Override
    public Patient selectByID(Long id) {
        Patient patientSelected = null;

        for (Patient patient : patientList) {
            if(Objects.equals(id, patient.id())){
                patientSelected = patient;
                log.info("Patient selected by ID: " + id + ". " + patient);
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
                log.info("Patient ID: " + patient.id() + ", successfully updated. " + patient1);
                return patient;
            }
        }
        log.error("error, id not found, patient not updated");
        return null;
    }

    @Override
    public Patient deleteByID(Long id) {

        for (Patient patient : patientList) {
            if (Objects.equals(id, patient.id())){
                patientList.remove(patient);
                log.info("Patient deleted by ID: " + id + ". " + patient);
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
