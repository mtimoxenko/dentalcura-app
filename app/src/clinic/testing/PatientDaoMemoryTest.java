package clinic.testing;

import clinic.entities.Patient;
import clinic.persistence.PatientDaoMemory;
import clinic.service.PatientService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientDaoMemoryTest {
    private static final Logger LOGGER = Logger.getLogger(PatientDaoMemory.class);

    @Test
    void insert() {
        LOGGER.info("initializing insert TEST (patient)");

        Patient patient = new Patient(4L,"Max","Check","Lake 123",555,"05/05/2023");

        PatientService patientService = new PatientService();
        patientService.setPatientIDao(new PatientDaoMemory());


        assertEquals(555,patientService.insertPatient(patient).niNumber());
    }
}