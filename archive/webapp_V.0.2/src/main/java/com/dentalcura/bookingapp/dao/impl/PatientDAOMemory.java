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
        log.info("Insert patient (running)");

        for (Patient patient1 : patientList) {
            if (Objects.equals(patient.id(), patient1.id())){
                log.info("Patient not added, id already exists");
                return null;
            }
        }
        patientList.add(patient);
        log.info("Data saved in memory: " + patient);
        return patient;
    }

    @Override
    public List<Patient> selectAll() {
        log.info("Retrieving patient data from memory...");
        patientList.forEach(patient -> log.info("Patient: " + patient));
        return patientList;
    }

    @Override
    public Patient selectByID(Long id) {
        log.info("Search patient by id");

        for (Patient patient : patientList) {
            if(Objects.equals(id, patient.id())){
                log.info("Patient selected from memory by ID: " + id + ". " + patient);
                return patient;
            }
        }
        log.info("Patient not found");
        return null;
    }

    @Override
    public Patient updateByID(Patient patient) {
        log.info("Update patient internal memory, running");

        for (Patient patient1 : patientList) {
            if (Objects.equals(patient.id(), patient1.id())){
                patientList.add(patient);
                patientList.remove(patient1);
                log.info("Patient ID: " + patient.id() + ", successfully updated. " + patient1);
                return patient;
            }
        }
        log.info("Patient not updated, id not found");
        return null;
    }

    @Override
    public Patient deleteByID(Long id) {
        log.info("Delete patient internal memory, running");

        for (Patient patient : patientList) {
            if (Objects.equals(id, patient.id())){
                patientList.remove(patient);
                log.info("Patient deleted by ID: " + id + ". " + patient);
                return patient;
            }
        }
        log.info("Patient not found, id not found");
        return null;
    }

    @Override
    public String toString() {
        return "internal-Memory";
    }
}
