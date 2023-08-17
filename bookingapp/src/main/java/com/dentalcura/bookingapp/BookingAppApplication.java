package com.dentalcura.bookingapp;

import com.dentalcura.bookingapp.dao.impl.*;
import com.dentalcura.bookingapp.model.Appointment;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.service.AppointmentService;
import com.dentalcura.bookingapp.service.DentistService;
import com.dentalcura.bookingapp.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@Slf4j
public class BookingAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookingAppApplication.class, args);

		log.info("Sequence init...");

		// execute once
		// agregar la creacion de la tabla Address
		 createTablesH2();


		log.info("Task execution finished");

	}


	private static void createTablesH2(){

		DentistService dentistService = new DentistService();
		PatientService patientService = new PatientService();
		AppointmentService appointmentService = new AppointmentService();

		// seteamos la estrategia de persistencia
		dentistService.setDentistIDao(new DentistDAOH2());
		patientService.setPatientIDao(new PatientDAOH2());
		appointmentService.setAppointmentIDao(new AppointmentDAOH2());

		// creamos las tablas en la DB
		dentistService.createTableDentist();
		patientService.createTablePatient();
		appointmentService.createTableAppointment();

	}

	// -----> H2
	private static void dentistH2(){

		Dentist dentist = new Dentist(1L,"Ramiro","Ranalli", 123456);
		Dentist dentist2 = new Dentist(2L,"Javier","Mascherano", 654321);

		DentistService dentistService = new DentistService();

		// seteamos la estrategia de persistencia
		dentistService.setDentistIDao(new DentistDAOH2());
		log.info("Persistence Layer: " + dentistService.getDentistIDao());

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
		log.info("Persistence Layer: " + patientService.getPatientIDao());

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
		log.info("Persistence Layer: " + appointmentService.getAppointmentIDao());

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




}
