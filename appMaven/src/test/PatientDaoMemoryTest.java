package test;

import org.clinic.entities.Patient;
import org.clinic.persistence.PatientDaoMemory;
import org.clinic.service.PatientService;
import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class PatientDaoMemoryTest {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(PatientDaoMemory.class));

    @Test
    public void insert() {
        LOGGER.info("initializing insert TEST (patient)");

        Patient patient = new Patient(4L,"Max","Check","Lake 123",555,"05/05/2023");

        PatientService patientService = new PatientService();
        patientService.setPatientIDao(new PatientDaoMemory());


        assertEquals(555,patientService.insertPatient(patient).getNiNumber());
    }
}