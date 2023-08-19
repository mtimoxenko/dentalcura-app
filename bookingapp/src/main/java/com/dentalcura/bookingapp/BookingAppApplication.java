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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

		// OK
		createDB();
		dentistH2();
		patientH2();

		// REVISION
		appointmentH2();


		log.info("Task execution finished");
	}


	private static void createDB(){
		drops();
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



	private static void dentistH2(){

		Dentist dentist1 = new Dentist(1L,"Jack","Ripper", 123456);
		Dentist dentist2 = new Dentist(2L,"Ted","Bundy", 654321);
		Dentist dentist3 = new Dentist(3L,"Jason","Voorhees", 987654);


		DentistService dentistService = new DentistService();
		// seteamos la estrategia de persistencia
		dentistService.setDentistIDao(new DentistDAOH2());
		log.info("Persistence Layer: " + dentistService.getDentistIDao());

		// insertamos objetos
		dentistService.insertDentist(dentist1);
		dentistService.insertDentist(dentist2);
		dentistService.insertDentist(dentist3);

		// listamos todos los registros
		dentistService.selectAllDentist();

		// listamos un registro por ID
		dentistService.selectDentistByID(1L);

		// eliminamos un registro por ID
		dentistService.deleteDentistByID(1L);

		// actualizamos un registro por ID
		dentistService.updateDentistByID(new Dentist(3L,"Norman","Bates",435698));
	}

	private static void patientH2(){

		Address address1 = new Address(1L, "Hundred Acre Wood", 22,10,"A");
		Patient patient1 = new Patient(1L,"Winnie", "Pooh", 1111, "12/01/1976", address1);

		Address address2 = new Address(2L, "The Shire, Middle-Earth", 1252,1,"B");
		Patient patient2 = new Patient(2L,"Frodo", "Baggins", 2222, "01/07/1985", address2);

		Address address3 = new Address(3L, "Hill Valley, California", 73468,10,"A");
		Patient patient3 = new Patient(3L,"Marty", "McFly", 542436, "12/01/1991", address3);



		PatientService patientService = new PatientService();
		// seteamos la estrategia de persistencia
		patientService.setPatientIDao(new PatientDAOH2());
		log.info("Persistence Layer: " + patientService.getPatientIDao());

		// insertamos objetos
		patientService.insertPatient(patient1);
		patientService.insertPatient(patient2);
		patientService.insertPatient(patient3);

		// listamos todos los registros
		patientService.selectAllPatient();

		// listamos un registro por ID
		patientService.selectPatientByID(1L);

		// eliminamos un registro por ID
		patientService.deletePatientByID(1L);

		// actualizamos un registro por ID
		// y si quiero actualizar address sin modificar id ?
		// aca meto un condicional de si o si coincidan los ID, sino arroja error exception.
		Address address4 = new Address(2L, "Hogwarts School", 100,2,"Z");
		patientService.updatePatientByID(new Patient(2L,"Harry","Potter",55555,"10/10/2023", address4));
	}


	private static void appointmentH2(){

		PatientService patientService = new PatientService();
		patientService.setPatientIDao(new PatientDAOH2());

		DentistService dentistService = new DentistService();
		dentistService.setDentistIDao(new DentistDAOH2());

		AppointmentService appointmentService = new AppointmentService();
		appointmentService.setAppointmentIDao(new AppointmentDAOH2());


		//	INSERT
		log.info("Searching for valid Patient and Dentist to register an Appointment...");
		Patient patient2 = patientService.selectPatientByID(2L);
		Dentist dentist2 = dentistService.selectDentistByID(2L);
		Patient patient3 = patientService.selectPatientByID(3L);
		Dentist dentist3 = dentistService.selectDentistByID(3L);

		Appointment appointment1 = new Appointment(1L,"01/10/2021", patient2, dentist2);
		Appointment appointment2 = new Appointment(2L,"10/12/2050", patient3, dentist3);
		// insertamos objetos
		appointmentService.insertAppointment(appointment1);
		appointmentService.insertAppointment(appointment2);

		// listamos todos los registros
		log.info("Searching for valid data to list all Appointments...");
		appointmentService.selectAllAppointment();


		// listamos un registro por ID
		appointmentService.selectAppointmentByID(1L);

		// eliminamos un registro por ID
		log.info("Searching for Appointment by ID to delete from DB...");
        appointmentService.deleteDAppointmentByID(1L);



		// actualizamos un registro por ID
		log.info("Searching for valid data to UPDATE an Appointment by ID...");
		// Validar el ID
		
		// cambiar el paciente
		Patient patient4 = patientService.selectPatientByID(3L);
		// cambiar el dentista
		Dentist dentist4 = dentistService.selectDentistByID(3L);
		// cambiar la fecha
		Appointment appointment4 = new Appointment(2L,"10/12/2051", patient4, dentist4);


		appointmentService.updateAppointmentByID(appointment4);
	}


}
