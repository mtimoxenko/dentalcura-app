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
		createDB();
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
