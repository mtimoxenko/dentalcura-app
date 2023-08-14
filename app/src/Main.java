import clinic.entities.Appointment;
import clinic.entities.Dentist;
import clinic.entities.Patient;
import clinic.persistence.*;
import clinic.service.AppointmentService;
import clinic.service.PatientService;
import org.apache.log4j.Logger;
import clinic.service.DentistService;



public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Sequence init...");

        // separar mas las funciones
        // crearTablas se ejecuta una sola vez
        

        // -----> H2
//        dentistH2();
//        patientH2();
//        appointmentH2();

        // -----> In-Memory
//        dentistMemory();
//        patientMemory();
        appointmentMemory();     //  <--- facus_branch



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
    private static void appointmentH2(){

        Patient patient2 = new Patient(2L,"Lola", "Rodriguez", "San Martin 1270", 93153234, "15/11/2022");
        Patient patientX = new Patient(2L,"X", "X", "X", 0, "X/X/X");

        Dentist dentist2 = new Dentist(2L,"Lionel","Messi",10);
        Dentist dentistX = new Dentist(2L,"Y","Y",100);



        Appointment appointment = new Appointment(1L,"11/22/33", patient2, dentist2);

        AppointmentService appointmentService = new AppointmentService();

        // seteamos la estrategia de persistencia
        appointmentService.setAppointmentIDao(new AppointmentDAOH2());
        LOGGER.info("Persistence Layer: " + appointmentService.getAppointmentIDao());

        // creamos la tabla en la DB
        appointmentService.createTableAppointment();

        // insertamos objetos
        appointmentService.insertAppointment(appointment);


        // listamos todos los registros
        appointmentService.selectAllAppointment();

        // listamos un registro por ID
        appointmentService.selectAppointmentByID(1L);

        // eliminamos un registro por ID
//        appointmentService.deleteDAppointmentByID(1L);

        // actualizamos un registro por ID
        appointmentService.updateAppointmentByID(new Appointment(1L,"00/00/00", patientX, dentistX));
    }

    // -----> In-Memory
    private static void dentistMemory(){

        Dentist dentist = new Dentist(1L,"Ramiro","Ranalli", 123456);
        Dentist dentist2 = new Dentist(2L,"Javier","Mascherano", 654321);
        Dentist dentist3 = new Dentist(1L,"Rename","Update",1010);

        DentistService dentistService = new DentistService();

            // seteamos la estrategia de persistencia
        dentistService.setDentistIDao(new DentistDAOMemory());
        LOGGER.info("Persistence Layer: " + dentistService.getDentistIDao());

            // insertamos objetos
        dentistService.insertDentist(dentist);
        dentistService.insertDentist(dentist2);
        dentistService.insertDentist(dentist3);

            // listamos todos los registros
        dentistService.selectAllDentist();
        dentistService.selectDentistByID(2L);
    }
    private static void patientMemory(){

        Patient patient = new Patient(1L,"Juan", "Perez", "Cuba 2628", 11223344, "01/01/2020");
        Patient patient2 = new Patient(2L,"Lola", "Rodriguez", "San Martin 1270", 93153234, "15/11/2022");

        PatientService patientService = new PatientService();

            // seteamos la estrategia de persistencia
        patientService.setPatientIDao(new PatientDAOMemory());
        LOGGER.info("Persistence Layer: " + patientService.getPatientIDao());

            // insertamos objetos
        patientService.insertPatient(patient);
        patientService.insertPatient(patient2);
        // listamos todos los registros
        patientService.selectAllPatient();
    }
    private static void appointmentMemory(){

//        Patient patient = new Patient(1L,"Juan", "Perez", "Cuba 2628", 11223344, "01/01/2020");
//        Patient patient2 = new Patient(2L,"Lola", "Rodriguez", "San Martin 1270", 93153234, "15/11/2022");
//
//        PatientService patientService = new PatientService();
//
//        // seteamos la estrategia de persistencia
//        patientService.setPatientIDao(new PatientDAOMemory());
//        LOGGER.info("Persistence Layer: " + patientService.getPatientIDao());
//
//        // insertamos objetos
//        patientService.insertPatient(patient);
//        patientService.insertPatient(patient2);
//        // listamos todos los registros
//        patientService.selectAllPatient();
    }

}