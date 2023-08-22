package com.dentalcura.bookingapp;

import com.dentalcura.bookingapp.dao.impl.*;
import com.dentalcura.bookingapp.model.*;
import com.dentalcura.bookingapp.service.AppointmentService;
import com.dentalcura.bookingapp.service.DentistService;
import com.dentalcura.bookingapp.service.PatientService;
import com.dentalcura.bookingapp.service.UserService;
import com.dentalcura.bookingapp.util.DB;
import com.dentalcura.bookingapp.util.SQLQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


@SpringBootApplication
@Slf4j
public class BookingAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookingAppApplication.class, args);

//		log.info("Sequence init...");

//		createDB();

//		userAdd(1L, "Maximo", "Timochenko", "mtimochenko@test.com", "123456", true);
//		userAdd(2L, "UserComun", "UserApellido", "user@test.com", "111111", false);

//		userListById(1L);
//		userListAll();
//		userDeleteById(2L);
//		userListAll();
//		userUpdateById(1L,"MaxPower","Timochenko","maxpower@test.com","MaxPower12345",false);
//		userListById(1L);
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
//
//		dentistListById(1L);
//		patientListById(1L);
//		appointmentById(1L);
//
//		dentistListAll();
//		patientListAll();
//		appointmentListAll();
//
//
//		dentistUpdateById(1L,"Max", "Timo", 11223344);
//		patientUpdateById(1L,"Michael","Jackson",929292,"02/12/1850","Smooth Criminal", 1010,2,"Y");
//		appointmentUpdateById(1L,"11/11/2011");
//
//		dentistDeleteById(1L);
//		patientDeleteById(2L);
//		appointmentDeleteById(2L);
//		log.info("Task execution finished");
	}


	// DB
	private static void createDB(){
		drops();
		log.info("[ Executing createDB() ]");
		PatientService patientService = new PatientService();
		DentistService dentistService = new DentistService();
		AppointmentService appointmentService = new AppointmentService();
		UserService userService = new UserService();


		// seteamos la estrategia de persistencia
		patientService.setPatientIDao(new PatientDAOH2());
		dentistService.setDentistIDao(new DentistDAOH2());
		appointmentService.setAppointmentIDao(new AppointmentDAOH2());
		userService.setUserIDao(new UserDAOH2());

		// creamos las tablas en la DB
		patientService.createTablePatient();
		dentistService.createTableDentist();
		appointmentService.createTableAppointment();
		userService.createTableUser();

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



	// USER
	private static void userAdd(Long id, String name, String surname, String email, String password, Boolean admin){
		log.info("[ Executing userAdd() ]");
		UserService userService = new UserService();
		// Persistence strategy
		userService.setUserIDao(new UserDAOH2());

		User user = new User(id, name, surname, email, password, admin);
		userService.insertUser(user);
		log.info("[ userAdd() finished ]");
	}
	private static void userListAll(){
		log.info("[ Executing userListAll() ]");
		UserService userService = new UserService();
		// Persistence strategy
		userService.setUserIDao(new UserDAOH2());

		userService.selectAllUser();
		log.info("[ userListAll() finished ]");
	}
	private static void userListById(Long id){
		log.info("[ Executing userListById() ]");
		UserService userService = new UserService();
		// Persistence strategy
		userService.setUserIDao(new UserDAOH2());

		userService.selectUserByID(id);
		log.info("[ userListById() finished ]");
	}
	private static void userDeleteById(Long id){
		log.info("[ Executing userDeleteById() ]");
		UserService userService = new UserService();
		// Persistence strategy
		userService.setUserIDao(new UserDAOH2());

		userService.deleteUserByID(id);
		log.info("[ userDeleteById() finished ]");
	}
	private static void userUpdateById(Long id, String name, String surname, String email, String password, Boolean admin){
		log.info("[ Executing userUpdateById() ]");
		UserService userService = new UserService();
		// Persistence strategy
		userService.setUserIDao(new UserDAOH2());

		User user = new User(id, name, surname, email, password, admin);
		userService.updateUserByID(user);
		log.info("[ userUpdateById() finished ]");
	}



	// PATIENT
	public static void patientAdd(Long id, String name, String surname, int niNumber, String regDate, String streetName, int streetNumber, int floor, String department){
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
		log.info("[ Executing dentistListById() ]");
		DentistService dentistService = new DentistService();
		// Persistence strategy
		dentistService.setDentistIDao(new DentistDAOH2());

		dentistService.selectDentistByID(id);
		log.info("[ dentistListById() finished ]");
	}
	private static void dentistDeleteById(Long id){
		log.info("[ Executing dentistDeleteById() ]");
		DentistService dentistService = new DentistService();
		// Persistence strategy
		dentistService.setDentistIDao(new DentistDAOH2());

		dentistService.deleteDentistByID(id);
		log.info("[ dentistDeleteById() finished ]");
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


}
