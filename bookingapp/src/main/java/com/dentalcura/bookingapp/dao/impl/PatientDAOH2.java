package com.dentalcura.bookingapp.dao.impl;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Address;
import com.dentalcura.bookingapp.model.Patient;
import com.dentalcura.bookingapp.util.DB;
import com.dentalcura.bookingapp.util.SQLQueries;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PatientDAOH2 implements IDao<Patient> {

    public void createTable(){
        Connection connection;
        Statement statement;


        // primero crea la tabla ADDRESS ?
        try {
            //               TURBIOOOOOOO!!!
            // aca llamaria al AddressDAOH2.insert
            AddressDAOH2 addressDAOH2 = new AddressDAOH2();
            addressDAOH2.createTable();
            // -------------------------------------



            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            statement = connection.createStatement();

            statement.execute(SQLQueries.PATIENT.getCreateTable());

            statement.close();
            connection.close();

            log.info("PATIENT table was created in DB");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Creating PATIENT table in DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    @Override // OK
    public Patient insert(Patient patient) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            //               TURBIOOOOOOO!!!
            // aca llama al AddressDAOH2.insert primero
            // para que no haya erro de inconsistencia por la FK
            AddressDAOH2 addressDAOH2 = new AddressDAOH2();
            addressDAOH2.insert(patient.address());
            // -------------------------------------

            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.PATIENT.getInsertCustom());

            preparedStatement.setLong(1, patient.id());
            preparedStatement.setString(2, patient.name());
            preparedStatement.setString(3, patient.surname());
            preparedStatement.setInt(4, patient.niNumber());
            preparedStatement.setString(5, patient.registrationDate());
            // fk address_id
            preparedStatement.setLong(6, patient.address().id());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("New reg ADDED to table [" + patient + "]");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Add new " + patient +  " to table was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Patient> selectAll() {

        Connection connection;
        PreparedStatement preparedStatement;
        List<Patient> patients = new ArrayList<>();

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.PATIENT.getSelectAll());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int niNumber = resultSet.getInt(4);
                String registrationDate = resultSet.getString(5);







                //               TURBIOOOOOOO!!!
                // condicional que matchee id del paciente con el de domicilio primero
                AddressDAOH2 addressDAOH2 = new AddressDAOH2();
                Address address = addressDAOH2.selectByID(id);
                // -------------------------------------





                Patient patient = new Patient(id, name, surname, niNumber, registrationDate, address);
                patients.add(patient);
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

        log.info("Rendering data for all Patients in DB...");
        patients.forEach(patient -> log.info("id [" + patient.id() + "] " + patient + " > " + patient.address()));

        return patients;
    }

    @Override
    public Patient selectByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        Patient patient = null;

        //               TURBIOOOOOOO!!!
        AddressDAOH2 addressDAOH2 = new AddressDAOH2();
        Address address = addressDAOH2.selectByID(id);
        // -------------------------------------

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.PATIENT.getSelectById());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long tabId = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int niNumber = resultSet.getInt(4);
                String regDate = resultSet.getString(5);

                patient = new Patient(tabId, name, surname, niNumber, regDate, address);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            log.info("Searching Patient by ID in DB...");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Retrieve Patient by ID from DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("Register id " + "[" + id + "] was found.");
        assert patient != null;
        log.info(patient + " > " + patient.address());

        return patient;
    }

    @Override
    public Patient updateByID(Patient patient) {
        Connection connection;
        PreparedStatement preparedStatement;
        log.info("Trying to UPDATE Patient by ID from DB...");

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.PATIENT.getUpdateById());

            preparedStatement.setLong(5, patient.id());

            preparedStatement.setString(1, patient.name());
            preparedStatement.setString(2, patient.surname());
//            preparedStatement.setString(3, patient.address());
            preparedStatement.setInt(3, patient.niNumber());
            preparedStatement.setString(4, patient.registrationDate());


            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();




            //               TURBIOOOOOOO!!!
            // aca llamaria al AddressDAOH2.insert
            AddressDAOH2 addressDAOH2 = new AddressDAOH2();
            addressDAOH2.updateByID(patient.address());
            // -------------------------------------





            log.info("Patient id " + "[" + patient.id() + "]" +" was UPDATED in table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Updating Patient id "  +  "[" + patient.id() + "]" + " was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("UPDATED [" + patient + " > " + patient.address() +"]");

        return patient;
    }

    @Override
    public Patient deleteByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
//        Patient patient = selectByID(id);

        try {

            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.PATIENT.getDeleteById());

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();


            //               TURBIOOOOOOO!!!
            AddressDAOH2 addressDAOH2 = new AddressDAOH2();
            addressDAOH2.deleteByID(id);
            // -------------------------------------


            log.info("Patient id " + "[" + id + "]" +" was deleted from table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Delete Patient id "  +  "[" + id + "]" + " from table was not possible");
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
