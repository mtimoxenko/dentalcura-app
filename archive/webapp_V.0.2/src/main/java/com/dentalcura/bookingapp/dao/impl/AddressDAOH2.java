package com.dentalcura.bookingapp.dao.impl;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Address;
import com.dentalcura.bookingapp.util.DB;
import com.dentalcura.bookingapp.util.SQLQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository("addressDAOH2")
public class AddressDAOH2 implements IDao<Address>{

    @Override
    public void createTable() {

    }

    @Override
    public Address insert(Address address) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.ADDRESS.getInsertCustom());

//            preparedStatement.setLong(1, address.id());
            preparedStatement.setString(1, address.streetName());
            preparedStatement.setInt(2, address.streetNumber());
            preparedStatement.setInt(3, address.floor());
            preparedStatement.setString(4, address.department());


            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("New reg ADDED to table [" + address + "]");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Add new " + address +  " to table was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Address> selectAll() {

        Connection connection;
        PreparedStatement preparedStatement;
        List<Address> addresses = new ArrayList<>();

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.ADDRESS.getSelectAll());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String streetName = resultSet.getString(2);
                Integer streetNumber = resultSet.getInt(3);
                Integer floor = resultSet.getInt(4);
                String department = resultSet.getString(5);


                Address address = new Address(id, streetName, streetNumber, floor, department);
                addresses.add(address);
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

        log.info("Rendering data for all Addresses in DB...");
        addresses.forEach(address -> log.info("id [" + address.id() + "] " + address));

        return addresses;
    }

    @Override
    public Address selectByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        Address address = null;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.ADDRESS.getSelectById());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String streetName = resultSet.getString(2);
                Integer streetNumber = resultSet.getInt(3);
                Integer floor = resultSet.getInt(4);
                String department = resultSet.getString(5);

                address = new Address(id, streetName, streetNumber, floor, department);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

//            log.info("Searching Address by ID in DB...");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Retrieve Address by ID from DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

//        log.info("Register id " + "[" + id + "] was found.");
//        log.info(String.valueOf(address));

        return address;
    }

    @Override
    public Address updateByID(Address address) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.ADDRESS.getUpdateById());

            preparedStatement.setLong(5, address.id());

            preparedStatement.setString(1, address.streetName());
            preparedStatement.setInt(2, address.streetNumber());
            preparedStatement.setInt(3, address.floor());
            preparedStatement.setString(4, address.department());


            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

//            log.info("Address id " + "[" + address.id() + "]" +" was UPDATED in table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Updating Address id "  +  "[" + address.id() + "]" + " was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

//        log.info("UPDATED [" + address + "]");

        return address;
    }

    @Override
    public Address deleteByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.ADDRESS.getDeleteById());

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

//            log.info("Address id " + "[" + id + "]" +" was DELETED from table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Delete Address id "  +  "[" + id + "]" + " from table was not possible");
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
