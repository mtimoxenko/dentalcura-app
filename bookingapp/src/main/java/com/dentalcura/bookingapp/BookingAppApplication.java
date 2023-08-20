package com.dentalcura.bookingapp;

import com.dentalcura.bookingapp.dao.impl.*;
import com.dentalcura.bookingapp.model.Address;
import com.dentalcura.bookingapp.model.Appointment;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.service.AppointmentService;
import com.dentalcura.bookingapp.service.DentistService;
import com.dentalcura.bookingapp.service.PatientService;
import com.dentalcura.bookingapp.util.DB;
import com.dentalcura.bookingapp.util.SQLQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@SpringBootApplication
@Slf4j
public class BookingAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookingAppApplication.class, args);

		log.info("Sequence init...");

//		createDB();
//
//
//		dentistAdd(1L,"Jack", "Ripper",123456);
//		dentistAdd(2L,"Ted", "Bundy",654321);
//		dentistAdd(3L,"Jason", "Voorhees",323232);
//		dentistAdd(4L,"Dr.", "Who",51515151);
//
//		patientAdd(1L,"Winnie","Pooh",93175123,"21/01/1983","Hundred Acre Wood",2023,8,"D");
//		patientAdd(2L,"Donnie","Darko",121212,"10/01/2020","Av. Belgrano",1122,10,"A");
//		patientAdd(3L,"Frodo","Baggins",93175123,"21/01/1983","he Shire, Middle-Earth",2023,1,"B");
//		patientAdd(4L,"Marty","McFly",93175123,"12/02/2000","Hill Valley, California",2626,35,"H");
//
//		appointmentAdd(1L,"12/12/2023",1L,4L);
//		appointmentAdd(2L,"01/12/2023",4L,2L);

//		dentistListById(1L);
//		patientListById(1L);
//		appointmentById(1L);

//		dentistListAll();
//		patientListAll();
//		appointmentListAll();


//		dentistUpdateById(1L,"Max", "Timo", 11223344);
//		patientUpdateById(1L,"Michael","Jackson",929292,"02/12/1850","Smooth Criminal", 1010,2,"Y");
//		appointmentUpdateById(1L,"11/11/2011");

//		dentistDeleteById(1L);
//		patientDeleteById(2L);
//		appointmentDeleteById(2L);

		log.info("Task execution finished");
	}


	private static void createDB(){
		drops();
		log.info("[ Executing createDB() ]");
		PatientService patientService = new PatientService();
		DentistService dentistService = new DentistService();
		AppointmentService appointmentService = new AppointmentService();

		// seteamos la estrategia de persistencia
		patientService.setPatientIDao(new PatientDAOH2());
		dentistService.setDentistIDao(new DentistDAOH2());
		appointmentService.setAppointmentIDao(new AppointmentDAOH2());

		// creamos las tablas en la DB
		patientService.createTablePatient();
		dentistService.createTableDentist();
		appointmentService.createTableAppointment();

		log.info("DB created");
	}
	private static void drops() {
		log.info("[ Executing drops() ]");
		try{
			Connection connection;
			Statement statement;

			Class.forName(DB.DRIVER);
			connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
			statement = connection.createStatement();

			statement.execute(SQLQueries.DROPS.getCreateTable());

			statement.close();
			connection.close();

			log.info("DB cleared");

		} catch (SQLException | ClassNotFoundException e) {
			log.error("Clearing DB was NOT possible");
			log.error(String.valueOf(e));
			throw new RuntimeException(e);
		}

	}




	// DENTIST
	private static void dentistAdd(Long id, String name, String surname, int licenseNumber){
		log.info("[ Executing dentistAdd() ]");
		DentistService dentistService = new DentistService();
		// Persistence strategy
		dentistService.setDentistIDao(new DentistDAOH2());

		Dentist dentist = new Dentist(id, name, surname, licenseNumber);
		dentistService.insertDentist(dentist);
		log.info("[ dentistAdd() finished ]");
	}
	private static void dentistListAll(){
		log.info("[ Executing dentistListAll() ]");
		DentistService dentistService = new DentistService();
		// Persistence strategy
		dentistService.setDentistIDao(new DentistDAOH2());

		dentistService.selectAllDentist();
		log.info("[ dentistListAll() finished ]");
	}
	private static void dentistListById(Long id){
		log.info("[ Executing denistListById() ]");
		DentistService dentistService = new DentistService();
		// Persistence strategy
		dentistService.setDentistIDao(new DentistDAOH2());

		dentistService.selectDentistByID(id);
		log.info("[ denistListById() finished ]");
	}
	private static void dentistDeleteById(Long id){
		log.info("[ Executing denistDeleteById() ]");
		DentistService dentistService = new DentistService();
		// Persistence strategy
		dentistService.setDentistIDao(new DentistDAOH2());

		dentistService.deleteDentistByID(id);
		log.info("[ denistDeleteById() finished ]");
	}
	private static void dentistUpdateById(Long id, String name, String surname, int licenseNumber){
		log.info("[ Executing dentistUpdateById() ]");
		DentistService dentistService = new DentistService();
		// Persistence strategy
		dentistService.setDentistIDao(new DentistDAOH2());

		Dentist dentist = new Dentist(id,name,surname,licenseNumber);
		dentistService.updateDentistByID(dentist);
		log.info("[ dentistUpdateById() finished ]");
	}
//	private static void dentistH2(){
//
//		Dentist dentist1 = new Dentist(1L,"Jack","Ripper", 123456);
//		Dentist dentist2 = new Dentist(2L,"Ted","Bundy", 654321);
//		Dentist dentist3 = new Dentist(3L,"Jason","Voorhees", 987654);
//
//
//		DentistService dentistService = new DentistService();
//		// seteamos la estrategia de persistencia
//		dentistService.setDentistIDao(new DentistDAOH2());
//		log.info("Persistence Layer: " + dentistService.getDentistIDao());
//
//		// insertamos objetos
//		dentistService.insertDentist(dentist1);
//		dentistService.insertDentist(dentist2);
//		dentistService.insertDentist(dentist3);
//
//		// listamos todos los registros
//		dentistService.selectAllDentist();
//
//		// listamos un registro por ID
//		dentistService.selectDentistByID(1L);
//
//		// eliminamos un registro por ID
//		dentistService.deleteDentistByID(1L);
//
//		// actualizamos un registro por ID
//		dentistService.updateDentistByID(new Dentist(3L,"Norman","Bates",435698));
//	}



	// PATIENT
	private static void patientAdd(Long id, String name, String surname, int niNumber, String regDate, String streetName, int streetNumber, int floor, String department){
		log.info("[ Executing patientAdd() ]");
		PatientService patientService = new PatientService();
		// Persistence strategy
		patientService.setPatientIDao(new PatientDAOH2());

		Address address = new Address(id, streetName, streetNumber, floor, department);
		Patient patient = new Patient(id, name, surname, niNumber, regDate, address);

		patientService.insertPatient(patient);
		log.info("[ patientAdd() finished ]");
	}
	private static void patientListAll(){
		log.info("[ Executing patientListAll() ]");
		PatientService patientService = new PatientService();
		// Persistence strategy
		patientService.setPatientIDao(new PatientDAOH2());

		patientService.selectAllPatient();
		log.info("[ patientListAll() finished ]");
	}
	private static void patientListById(Long id){
		log.info("[ Executing patientListById() ]");
		PatientService patientService = new PatientService();
		// Persistence strategy
		patientService.setPatientIDao(new PatientDAOH2());

		patientService.selectPatientByID(id);
		log.info("[ patientListById() finished ]");
	}
	private static void patientDeleteById(Long id){
		log.info("[ Executing patientDeleteById() ]");
		PatientService patientService = new PatientService();
		// Persistence strategy
		patientService.setPatientIDao(new PatientDAOH2());

		patientService.deletePatientByID(id);
		log.info("[ patientDeleteById() finished ]");
	}
	private static void patientUpdateById(Long id, String name, String surname, int niNumber, String regDate, String streetName, int streetNumber, int floor, String department){
		log.info("[ Executing patientUpdateById() ]");
		PatientService patientService = new PatientService();
		// Persistence strategy
		patientService.setPatientIDao(new PatientDAOH2());

		Patient patient = patientService.selectPatientByID(id);

		Address newAddress = new Address(patient.address().id(), streetName, streetNumber, floor, department);
		Patient newPatient = new Patient(patient.id(), name, surname, niNumber, regDate, newAddress);
		patientService.updatePatientByID(newPatient);
		log.info("[ patientUpdateById() finished ]");
	}
//	private static void patientH2(){
//
//		Address address1 = new Address(1L, "Hundred Acre Wood", 22,10,"A");
//		Patient patient1 = new Patient(1L,"Winnie", "Pooh", 1111, "12/01/1976", address1);
//
//		Address address2 = new Address(2L, "The Shire, Middle-Earth", 1252,1,"B");
//		Patient patient2 = new Patient(2L,"Frodo", "Baggins", 2222, "01/07/1985", address2);
//
//		Address address3 = new Address(3L, "Hill Valley, California", 73468,10,"A");
//		Patient patient3 = new Patient(3L,"Marty", "McFly", 542436, "12/01/1991", address3);
//
//
//
//		PatientService patientService = new PatientService();
//		// seteamos la estrategia de persistencia
//		patientService.setPatientIDao(new PatientDAOH2());
//		log.info("Persistence Layer: " + patientService.getPatientIDao());
//
//		// insertamos objetos
//		patientService.insertPatient(patient1);
//		patientService.insertPatient(patient2);
//		patientService.insertPatient(patient3);
//
//		// listamos todos los registros
//		patientService.selectAllPatient();
//
//		// listamos un registro por ID
//		patientService.selectPatientByID(1L);
//
//		// eliminamos un registro por ID
//		patientService.deletePatientByID(1L);
//
//		// actualizamos un registro por ID
//		// y si quiero actualizar address sin modificar id ?
//		// aca meto un condicional de si o si coincidan los ID, sino arroja error exception.
//		Address address4 = new Address(2L, "Hogwarts School", 100,2,"Z");
//		patientService.updatePatientByID(new Patient(2L,"Harry","Potter",55555,"10/10/2023", address4));
//	}



	// APPOINTMENT
	private static void appointmentAdd(Long id, String date, Long patientID, Long dentistID){
		log.info("[ Executing apointmentAdd() ]");
		PatientService patientService = new PatientService();
		DentistService dentistService = new DentistService();
		AppointmentService appointmentService = new AppointmentService();
		// Persistence strategy
		patientService.setPatientIDao(new PatientDAOH2());
		dentistService.setDentistIDao(new DentistDAOH2());
		appointmentService.setAppointmentIDao(new AppointmentDAOH2());

		//	INSERT
		Patient patient = patientService.selectPatientByID(patientID);
		Dentist dentist = dentistService.selectDentistByID(dentistID);

		Appointment appointment = new Appointment(id,date, patient, dentist);
		appointmentService.insertAppointment(appointment);
		log.info("[ apointmentAdd() finished ]");
	}
	private static void appointmentListAll(){
		log.info("[ Executing apointmentListAll() ]");
		AppointmentService appointmentService = new AppointmentService();
		// Persistence strategy
		appointmentService.setAppointmentIDao(new AppointmentDAOH2());

		appointmentService.selectAllAppointment();
		log.info("[ apointmentListAll() finished ]");
	}
	private static void appointmentById(Long id){
		log.info("[ Executing apointmentById() ]");
		AppointmentService appointmentService = new AppointmentService();
		// Persistence strategy
		appointmentService.setAppointmentIDao(new AppointmentDAOH2());

		appointmentService.selectAppointmentByID(id);
		log.info("[ apointmentById() finished ]");
	}
	private static void appointmentDeleteById(Long id){
		log.info("[ Executing apointmentDeleteById() ]");
		AppointmentService appointmentService = new AppointmentService();
		// Persistence strategy
		appointmentService.setAppointmentIDao(new AppointmentDAOH2());

		appointmentService.deleteDAppointmentByID(id);
		log.info("[ apointmentDeleteById() finished ]");
	}
	private static void appointmentUpdateById(Long id, String date){
		log.info("[ Executing apointmentUpdateById() ]");
		AppointmentService appointmentService = new AppointmentService();
		// Persistence strategy
		appointmentService.setAppointmentIDao(new AppointmentDAOH2());

		Appointment appointment = appointmentService.selectAppointmentByID(id);
		Appointment newAppointment = new Appointment(id, date, appointment.patient(), appointment.dentist());
		appointmentService.updateAppointmentByID(newAppointment);
		log.info("[ apointmentUpdateById() finished ]");
	}
//	private static void appointmentH2(){
//
//		PatientService patientService = new PatientService();
//		patientService.setPatientIDao(new PatientDAOH2());
//
//		DentistService dentistService = new DentistService();
//		dentistService.setDentistIDao(new DentistDAOH2());
//
//		AppointmentService appointmentService = new AppointmentService();
//		appointmentService.setAppointmentIDao(new AppointmentDAOH2());
//
//
//		//	INSERT
//		log.info("Searching for valid Patient and Dentist to register an Appointment...");
//		Patient patient2 = patientService.selectPatientByID(2L);
//		Dentist dentist2 = dentistService.selectDentistByID(2L);
//		Patient patient3 = patientService.selectPatientByID(3L);
//		Dentist dentist3 = dentistService.selectDentistByID(3L);
//
//		Appointment appointment1 = new Appointment(1L,"01/10/2021", patient2, dentist2);
//		Appointment appointment2 = new Appointment(2L,"10/12/2050", patient3, dentist3);
//		// insertamos objetos
//		appointmentService.insertAppointment(appointment1);
//		appointmentService.insertAppointment(appointment2);
//
//
//
//
//
//		// listamos todos los registros
//		log.info("Searching for valid data to list all Appointments...");
//		appointmentService.selectAllAppointment();
//
//
//		// listamos un registro por ID
//		appointmentService.selectAppointmentByID(1L);
//
//		// eliminamos un registro por ID
//		log.info("Searching for Appointment by ID to delete from DB...");
//        appointmentService.deleteDAppointmentByID(1L);
//
//
//
//		// actualizamos un registro por ID
//		log.info("Searching for valid data to UPDATE an Appointment by ID...");
//		// Validar el ID
//
//		// cambiar el paciente
//		Patient patient4 = patientService.selectPatientByID(3L);
//		// cambiar el dentista
//		Dentist dentist4 = dentistService.selectDentistByID(3L);
//		// cambiar la fecha
//		Appointment appointment4 = new Appointment(2L,"10/12/2051", patient4, dentist4);
//
//
//		appointmentService.updateAppointmentByID(appointment4);
//	}


}
