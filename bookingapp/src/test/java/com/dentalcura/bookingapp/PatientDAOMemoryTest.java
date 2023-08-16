package com.dentalcura.bookingapp;

import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.dao.impl.PatientDAOMemory;
import com.dentalcura.bookingapp.service.PatientService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PatientDAOMemoryTest {
    private static final Logger LOGGER = Logger.getLogger(PatientDAOMemory.class);

    @Test
    void insert() {
        LOGGER.info("initializing insert TEST (patient)");

        Patient patient = new Patient(4L,"Max","Check","Lake 123",555,"05/05/2023");
        Patient patient2 = new Patient(4L,"Max","Check","New York 2020",555,"05/05/2023");
        Patient patient3 = new Patient(5L,"Test","Update","Tokio 44",30,"30/05/2023");

        PatientService patientService = new PatientService();
        patientService.setPatientIDao(new PatientDAOMemory());

        assertEquals(4,patientService.insertPatient(patient).id());
        assertEquals("Max",patientService.selectPatientByID(4L).name());
        assertEquals("New York 2020",patientService.updatePatientByID(patient2).address());
        patientService.deletePatientByID(4L);
        assertNull(patientService.updatePatientByID(patient3));

        patientService.insertPatient(patient3);
        patientService.insertPatient(patient2);
        patientService.insertPatient(patient);
        patientService.updatePatientByID(patient);
        patientService.deletePatientByID(5L);
        assertEquals("[Max Check, ni_number: 555]",patientService.selectAllPatient().toString());
        assertEquals("Lake 123",patientService.selectPatientByID(4L).address());
        assertEquals(null,patientService.selectPatientByID(8L));

    }
}