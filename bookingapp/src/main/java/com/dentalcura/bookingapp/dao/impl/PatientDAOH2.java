package com.dentalcura.bookingapp.dao.impl;

import com.dentalcura.bookingapp.dao.IDao;
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

        try {
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

    @Override
    public Patient insert(Patient patient) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.PATIENT.getInsertCustom());

            preparedStatement.setLong(1, patient.id());
            preparedStatement.setString(2, patient.name());
            preparedStatement.setString(3, patient.surname());
            // ----------------------------------------------------------
//            preparedStatement.setString(4, patient.address());
            // ----------------------------------------------------------
            preparedStatement.setInt(5, patient.niNumber());
            preparedStatement.setString(6, patient.registrationDate());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("New reg ADDED to table [" + patient + "]");


            // aca llamaria al AddressDAOH2.insert

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
                String address = resultSet.getString(4);
                int niNumber = resultSet.getInt(5);
                String registrationDate = resultSet.getString(6);


                //  Domicilio <- listarPorIdDomicilio(id_PACIENTE)


                Patient patient = new Patient(id,name, surname, Domicilio <- listarPorIdDomicilio(id_PACIENTE) , niNumber, registrationDate);
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
        patients.forEach(patient -> log.info("id [" + patient.id() + "] " + patient));

        return patients;
    }

    @Override
    public Patient selectByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        Patient patient = null;

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
                String address = resultSet.getString(4);
                int niNumber = resultSet.getInt(5);
                String regDate = resultSet.getString(6);


//               AddressDAOH2.selectById ?


                patient = new Patient(tabId, name, surname, address, niNumber, regDate);
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
        log.info(String.valueOf(patient));

        return patient;
    }

    @Override
    public Patient updateByID(Patient patient) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.PATIENT.getUpdateById());

            preparedStatement.setLong(6, patient.id());

            preparedStatement.setString(1, patient.name());
            preparedStatement.setString(2, patient.surname());
//            preparedStatement.setString(3, patient.address());
            preparedStatement.setInt(4, patient.niNumber());
            preparedStatement.setString(5, patient.registrationDate());


            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

//            AddressDAOH2.updateById

            log.info("Patient id  " + "[" + patient.id() + "]" +" was UPDATED in table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Updating Patient id "  +  "[" + patient.id() + "]" + " was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("UPDATED [" + patient + "]");

        return patient;
    }

    @Override
    public Patient deleteByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;

        // primero borra address?
        // AddressDAOH2.deleteByID

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.PATIENT.getDeleteById());

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("Patient id  " + "[" + id + "]" +" was deleted from table");

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
