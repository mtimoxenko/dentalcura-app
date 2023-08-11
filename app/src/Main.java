import clinic.entities.Dentist;
import clinic.entities.Patient;
import clinic.persistence.PatientDAOH2;
import clinic.service.PatientService;
import org.apache.log4j.Logger;
import clinic.persistence.DentistDAOH2;
import clinic.persistence.DentistDAOInMemory;
import clinic.service.DentistService;


// Pasar a INGLES -> atributos/metodos - LOGGERS - tablas en la DB
// TESTS
// METODOS devuelven algo?
// BORRAR archivo LOG

public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Sequence init...");

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



        // cambiamos la estrategia de persistencia
        dentistService.setDentistIDao(new DentistDAOInMemory());
        LOGGER.info("Persistence Layer: " + dentistService.getDentistIDao());

        dentistService.insertDentist(dentist);
        dentistService.insertDentist(dentist2);
        dentistService.selectAllDentist();







        







        Patient patient = new Patient(1L,"Juan", "Perez", "Cuba 2628", 11223344, "01/01/2020");
        Patient patient2 = new Patient(2L,"Lola", "Rodriguez", "San Martin 1270", 93153234, "15/11/2022");


        PatientService patientService = new PatientService();
//        // seteamos una estrategia de persistencia
        patientService.setPatientIDao(new PatientDAOH2());

        LOGGER.info("Persistence Layer: " + patientService.getPatientIDao());
//
//        // creamos la tabla en la DB
        patientService.createTablePatient();
//
//        // insertamos objetos
        patientService.insertPatient(patient);
        patientService.insertPatient(patient2);
//
//        // listamos todos los registros
        patientService.selectAllPatient();

//        // cambiamos la estrategia de persistencia
        //        odontologoService.setOdontologoIDao(new OdontologoDaoEnMemoria());
//        LOGGER.info("Persistence Layer: " + odontologoService.getOdontologoIDao());

//
//        odontologoService.guardarOdontologo(odontologo);
//        odontologoService.guardarOdontologo(odontologo2);
//        odontologoService.listarTodosOdontologos();
//
        LOGGER.info("Task execution finished");

    }

}