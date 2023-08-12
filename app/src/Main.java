import clinic.entities.Dentist;
import clinic.entities.Patient;
import clinic.persistence.DentistDaoMemory;
import clinic.persistence.PatientDAOH2;
import clinic.persistence.PatientDaoMemory;
import clinic.service.PatientService;
import org.apache.log4j.Logger;
import clinic.persistence.DentistDAOH2;
import clinic.service.DentistService;



public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Sequence init...");



        // -------- DENTIST -----------

        // -----> H2
        Dentist dentist = new Dentist(1L,"Ramiro","Ranalli", 123456);
        Dentist dentist2 = new Dentist(2L,"Javier","Mascherano", 654321);

        DentistService dentistService = new DentistService();

            // seteamos una estrategia de persistencia
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


        // -----> MEMORY
            // cambiamos la estrategia de persistencia
        dentistService.setDentistIDao(new DentistDaoMemory());
        LOGGER.info("Persistence Layer: " + dentistService.getDentistIDao());

        dentistService.insertDentist(dentist);
        dentistService.insertDentist(dentist2);
        dentistService.selectAllDentist();













        // -------- PATIENT -----------

        // -----> H2
        Patient patient = new Patient(1L,"Juan", "Perez", "Cuba 2628", 11223344, "01/01/2020");
        Patient patient2 = new Patient(2L,"Lola", "Rodriguez", "San Martin 1270", 93153234, "15/11/2022");

        PatientService patientService = new PatientService();


            // seteamos una estrategia de persistencia
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





        // -----> MEMORY
            // cambiamos la estrategia de persistencia
        patientService.setPatientIDao(new PatientDaoMemory());
        LOGGER.info("Persistence Layer: " + patientService.getPatientIDao());


        patientService.insertPatient(patient);
        patientService.insertPatient(patient2);
        patientService.selectAllPatient();

        LOGGER.info("Task execution finished");

    }

}