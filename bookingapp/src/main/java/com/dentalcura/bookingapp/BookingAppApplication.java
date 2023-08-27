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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@SpringBootApplication
@Slf4j
public class BookingAppApplication {



	public static void main(String[] args) {
		SpringApplication.run(BookingAppApplication.class, args);
<<<<<<< HEAD

//		log.info("Sequence init...");
//<<<<<<< HEAD

//		createDB();

//		userAdd(1L, "Maximo", "Timochenko", "mtimochenko@test.com", "123456", true);
//		userAdd(2L, "UserComun", "UserApellido", "user@test.com", "111111", false);

//		userListById(1L);
/*		userListAll();
		userDeleteById(2L);
		userListAll();
		userUpdateById(1L,"MaxPower","Timochenko","maxpower@test.com","MaxPower12345",false);
		userListById(1L);

		dentistAdd(1L,"Jack", "Ripper",123456);
		dentistAdd(2L,"Ted", "Bundy",654321);
		dentistAdd(3L,"Jason", "Voorhees",323232);
		dentistAdd(4L,"Dr.", "Who",51515151);

		patientAdd(1L,"Winnie","Pooh",93175123,"21/01/1983","Hundred Acre Wood",2023,8,"D");
		patientAdd(2L,"Donnie","Darko",121212,"10/01/2020","Av. Belgrano",1122,10,"A");
		patientAdd(3L,"Frodo","Baggins",93175123,"21/01/1983","he Shire, Middle-Earth",2023,1,"B");
		patientAdd(4L,"Marty","McFly",93175123,"12/02/2000","Hill Valley, California",2626,35,"H");

		appointmentAdd(1L,"12/12/2023",1L,4L);
		appointmentAdd(2L,"01/12/2023",4L,2L);

		dentistListById(1L);
		patientListById(1L);
		appointmentById(1L);

		dentistListAll();
		patientListAll();
		appointmentListAll(); */
//=======
//
//		createDB();
//
//		userAdd(1L, "Maximo", "Timochenko", "mtimochenko@test.com", "123456", true);
//		userAdd(2L, "UserComun", "UserApellido", "user@test.com", "111111", false);
//
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
//>>>>>>> 4588f004ee96bc8a2c6acaab2caed74899fa30a0
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
=======
//		createDB();
>>>>>>> c8b40bcb99acdaa4ee9d42c0fe2d820099888d0e
	}

	public static void createDB(){

		Connection connection;
		Statement statement;

		try {
			Class.forName(DB.DRIVER);
			connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
			statement = connection.createStatement();

			statement.execute(SQLQueries.DROPS.getCreateTable());

			statement.execute(SQLQueries.ADDRESS.getCreateTable());
			statement.execute(SQLQueries.PATIENT.getCreateTable());
			statement.execute(SQLQueries.DENTIST.getCreateTable());
			statement.execute(SQLQueries.APPOINTMENT.getCreateTable());
			statement.execute(SQLQueries.USERS.getCreateTable());

			statement.close();
			connection.close();

		} catch (SQLException | ClassNotFoundException e) {
			log.error("Creating USR table in DB was not possible");
			log.error(String.valueOf(e));
			throw new RuntimeException(e);
		}


		log.info("DB created");
	}

}
