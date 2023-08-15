package com.dentalcura.bookingapp.dao.impl;


import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Appointment;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.sql.SQLQueries;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AppointmentDAOH2 implements IDao<Appointment>{

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    public void createTable(){
        Connection connection;
        Statement statement;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statement = connection.createStatement();

            statement.execute(SQLQueries.APPOINTMENT.getCreateTable());

            statement.close();
            connection.close();

            log.info("APPOINTMENT table was created in DB");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Creating APPOINTMENT table in DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    @Override
    public Appointment insert(Appointment appointment) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.APPOINTMENT.getInsertCustom());

            preparedStatement.setLong(1, appointment.getId());
            preparedStatement.setString(2, appointment.getDate());
            preparedStatement.setLong(3, appointment.getPatient().id());
            preparedStatement.setLong(4, appointment.getDentist().id());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("New reg ADDED to table [" + appointment + "]");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Add new " + appointment +  " to table was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Appointment> selectAll() {

        Connection connection;
        PreparedStatement preparedStatement;
        List<Appointment> appointments = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.APPOINTMENT.getSelectAll());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String date = resultSet.getString(2);
                Long patient_id = resultSet.getLong(3);
                Long dentist_id = resultSet.getLong(4);

                Patient patient = new Patient(patient_id, "patient_nameTest","patient_surnameTest","patient_addressTest",0,"patient_red_date_test" );
                Dentist dentist = new Dentist(dentist_id, "dentist_nameTest", "dentist_surnameTest",0);

                Appointment appointment = new Appointment(id, date, patient,dentist);
                appointments.add(appointment);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            log.info("Reading data from DB...");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Read data from DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("Rendering data for all Appointments in DB...");
        appointments.forEach(appointment -> log.info("id [" + appointment.getId() + "] " + appointment));

        return appointments;
    }

    @Override
    public Appointment selectByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        Appointment appointment = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.APPOINTMENT.getSelectById());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String date = resultSet.getString(2);
                Long patient_id = resultSet.getLong(3);
                Long dentist_id = resultSet.getLong(4);

                Patient patient = new Patient(patient_id, "patient_nameTest","patient_surnameTest","patient_addressTest",0,"patient_red_date_test" );
                Dentist dentist = new Dentist(dentist_id, "dentist_nameTest", "dentist_surnameTest",0);

                appointment = new Appointment(id, date, patient,dentist);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            log.info("Searching Appointment by ID in DB...");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Retrieve Appointment by ID from DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("Register id " + "[" + id + "] was found.");
        log.info(String.valueOf(appointment));

        return appointment;
    }

    @Override
    public Appointment updateByID(Appointment appointment) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.APPOINTMENT.getUpdateById());

            preparedStatement.setLong(4, appointment.getId());

            preparedStatement.setString(1, appointment.getDate());
            preparedStatement.setLong(2, appointment.getPatient().id());
            preparedStatement.setLong(3, appointment.getDentist().id());


            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("Appointment id  " + "[" + appointment.getId() + "]" +" was UPDATED in table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Updating Appointment id "  +  "[" + appointment.getId() + "]" + " was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("UPDATED [" + appointment + "]");

        return appointment;
    }

    @Override
    public Appointment deleteByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.APPOINTMENT.getDeleteById());

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("Appointment id  " + "[" + id + "]" +" was deleted from table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Delete Appointment id "  +  "[" + id + "]" + " from table was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public String toString() {
        return "H2 relational database";
    }


}
