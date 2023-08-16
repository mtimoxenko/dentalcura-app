package com.dentalcura.bookingapp.dao.impl;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.util.DB;
import com.dentalcura.bookingapp.util.SQLQueries;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DentistDAOH2 implements IDao<Dentist>{

    public void createTable(){
        Connection connection;
        Statement statement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            statement = connection.createStatement();

            statement.execute(SQLQueries.DENTIST.getCreateTable());

            statement.close();
            connection.close();

            log.info("DENTIST table was created in DB");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Creating DENTIST table in DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    @Override
    public Dentist insert(Dentist dentist) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.DENTIST.getInsertCustom());

            preparedStatement.setLong(1, dentist.id());
            preparedStatement.setString(2, dentist.name());
            preparedStatement.setString(3, dentist.surname());
            preparedStatement.setInt(4, dentist.licenseNumber());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("New reg ADDED to table [" + dentist + "]");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Add new " + dentist +  " to table was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Dentist> selectAll() {

        Connection connection;
        PreparedStatement preparedStatement;
        List<Dentist> dentists = new ArrayList<>();

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.DENTIST.getSelectAll());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int licenseNumber = resultSet.getInt(4);

                Dentist dentist = new Dentist(id,name, surname,licenseNumber);
                dentists.add(dentist);
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

        log.info("Rendering data for all Dentists in DB...");
        dentists.forEach(dentist -> log.info("id [" + dentist.id() + "] " + dentist));

        return dentists;
    }

    @Override
    public Dentist selectByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        Dentist dentist = null;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.DENTIST.getSelectById());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long tabId = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int licenseNumber = resultSet.getInt(4);
                dentist = new Dentist(tabId, name, surname,licenseNumber);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            log.info("Searching Dentist by ID in DB...");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Retrieve Dentist by ID from DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("Register id " + "[" + id + "] was found.");
        log.info(String.valueOf(dentist));

        return dentist;
    }

    @Override
    public Dentist updateByID(Dentist dentist) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.DENTIST.getUpdateById());

            preparedStatement.setLong(4, dentist.id());

            preparedStatement.setString(1, dentist.name());
            preparedStatement.setString(2, dentist.surname());
            preparedStatement.setInt(3, dentist.licenseNumber());


            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("Dentist id " + "[" + dentist.id() + "]" +" was UPDATED in table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Updating Dentist id "  +  "[" + dentist.id() + "]" + " was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("UPDATED [" + dentist + "]");

        return dentist;
    }

    @Override
    public Dentist deleteByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.DENTIST.getDeleteById());

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("Dentist id " + "[" + id + "]" +" was DELETED from table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Delete Dentist id "  +  "[" + id + "]" + " from table was not possible");
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
