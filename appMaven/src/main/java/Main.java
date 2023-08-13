import org.clinic.entities.Dentist;
import org.clinic.entities.Patient;
import org.clinic.persistence.DentistDAOH2;
import org.clinic.persistence.PatientDAOH2;
import org.clinic.persistence.DentistDaoMemory;
import org.clinic.persistence.PatientDaoMemory;
import org.clinic.service.PatientService;
import org.clinic.service.DentistService;

import java.util.logging.Logger;


public class Main {
    private final static Logger LOGGER = Logger.getLogger(String.valueOf(Main.class));

    public static void main(String[] args) {

        LOGGER.info("Sequence init...");

        // -----> H2
        dentistH2();
        patientH2();

        // -----> In-Memory
        dentistMemory();
        patientMemory();

        LOGGER.info("Task execution finished");

    }

    // -----> H2
    private static void dentistH2(){

        Dentist dentist = new Dentist(1L,"Ramiro","Ranalli", 123456);
        Dentist dentist2 = new Dentist(2L,"Javier","Mascherano", 654321);

        DentistService dentistService = new DentistService();

            // seteamos la estrategia de persistencia
        dentistService.setDentistIDao(new DentistDAOH2());
        LOGGER.info("Persistence Layer: " + dentistService.getDentistIDao());

            // creamos la tabla en la DB
        dentistService.createTableDentist();

            // insertamos objetos
        dentistService.insertDentist(dentist);
        dentistService.insertDentist(dentist2);

            // listamos todos los registros
        dentistService.selectAllDentist();

            // listamos un registro por ID
        dentistService.selectDentistByID(1L);

            // eliminamos un registro por ID
        dentistService.deleteDentistByID(1L);

            // actualizamos un registro por ID
        dentistService.updateDentistByID(new Dentist(2L,"Lionel","Messi",10));
    }
    private static void patientH2(){

        Patient patient = new Patient(1L,"Juan", "Perez", "Cuba 2628", 11223344, "01/01/2020");
        Patient patient2 = new Patient(2L,"Lola", "Rodriguez", "San Martin 1270", 93153234, "15/11/2022");

        PatientService patientService = new PatientService();

            // seteamos la estrategia de persistencia
        patientService.setPatientIDao(new PatientDAOH2());
        LOGGER.info("Persistence Layer: " + patientService.getPatientIDao());

            // creamos la tabla en la DB
        patientService.createTablePatient();

            // insertamos objetos
        patientService.insertPatient(patient);
        patientService.insertPatient(patient2);

            // listamos todos los registros
        patientService.selectAllPatient();

            // listamos un registro por ID
        patientService.selectPatientByID(1L);

            // eliminamos un registro por ID
        patientService.deletePatientByID(1L);

            // actualizamos un registro por ID
        patientService.updatePatientByID(new Patient(2L,"Juan","Carlos","Rivadavia 1050",93123654,"1/1/1980"));
    }

    // -----> In-Memory
    private static void dentistMemory(){

        Dentist dentist = new Dentist(1L,"Ramiro","Ranalli", 123456);
        Dentist dentist2 = new Dentist(2L,"Javier","Mascherano", 654321);

        DentistService dentistService = new DentistService();

            // seteamos la estrategia de persistencia
        dentistService.setDentistIDao(new DentistDaoMemory());
        LOGGER.info("Persistence Layer: " + dentistService.getDentistIDao());

            // insertamos objetos
        dentistService.insertDentist(dentist);
        dentistService.insertDentist(dentist2);

            // listamos todos los registros
        dentistService.selectAllDentist();
    }
    private static void patientMemory(){

        Patient patient = new Patient(1L,"Juan", "Perez", "Cuba 2628", 11223344, "01/01/2020");
        Patient patient2 = new Patient(2L,"Lola", "Rodriguez", "San Martin 1270", 93153234, "15/11/2022");

        PatientService patientService = new PatientService();

            // seteamos la estrategia de persistencia
        patientService.setPatientIDao(new PatientDaoMemory());
        LOGGER.info("Persistence Layer: " + patientService.getPatientIDao());

            // insertamos objetos
        patientService.insertPatient(patient);
        patientService.insertPatient(patient2);
        // listamos todos los registros
        patientService.selectAllPatient();
    }

}