package com.dentalcura.bookingapp.dao.impl;

import com.dentalcura.bookingapp.dao.IDao;
import com.dentalcura.bookingapp.model.Dentist;
import com.dentalcura.bookingapp.model.User;
import com.dentalcura.bookingapp.util.DB;
import com.dentalcura.bookingapp.util.SQLQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class UserDAOH2 implements IDao<User>{

    public void createTable(){
        Connection connection;
        Statement statement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            statement = connection.createStatement();

            statement.execute(SQLQueries.USERS.getCreateTable());

            statement.close();
            connection.close();

            log.info("USR table was created in DB");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Creating USR table in DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    @Override
    public User insert(User user) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.USERS.getInsertCustom());

            preparedStatement.setLong(1, user.id());
            preparedStatement.setString(2, user.name());
            preparedStatement.setString(3, user.surname());
            preparedStatement.setString(4, user.email());
            preparedStatement.setString(5, user.password());
            preparedStatement.setBoolean(6, user.admin());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("New reg ADDED to table [" + user + "]");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Add new " + user +  " to table was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<User> selectAll() {

        Connection connection;
        PreparedStatement preparedStatement;
        List<User> users = new ArrayList<>();

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.USERS.getSelectAll());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(5);
                Boolean admin = resultSet.getBoolean(6);


                User user = new User(id,name, surname, email, password, admin);
                users.add(user);
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

        log.info("Rendering data for all Users in DB...");
        users.forEach(user -> log.info("id [" + user.id() + "] " + user));

        return users;
    }

    @Override
    public User selectByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;
        User user = null;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.USERS.getSelectById());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(5);
                Boolean admin = resultSet.getBoolean(6);

                user = new User(id,name, surname, email, password, admin);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            log.info("Searching User by ID in DB...");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Retrieve User by ID from DB was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("Register id " + "[" + id + "] was found.");
        log.info(String.valueOf(user));

        return user;
    }

    @Override
    public User updateByID(User user) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.USERS.getUpdateById());

            preparedStatement.setLong(5, user.id());

            preparedStatement.setString(1, user.name());
            preparedStatement.setString(2, user.surname());
            preparedStatement.setString(3, user.email());
            preparedStatement.setBoolean(4, user.admin());


            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("User id " + "[" + user.id() + "]" +" was UPDATED in table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Updating Dentist id "  +  "[" + user.id() + "]" + " was not possible");
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        }

        log.info("UPDATED [" + user + "]");

        return user;
    }

    @Override
    public User deleteByID(Long id) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB.DRIVER);
            connection = DriverManager.getConnection(DB.URL,DB.USR,DB.PWD);
            preparedStatement = connection.prepareStatement(SQLQueries.USERS.getDeleteById());

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            log.info("User id " + "[" + id + "]" +" was DELETED from table");

        } catch (SQLException | ClassNotFoundException e) {
            log.error("Delete User id "  +  "[" + id + "]" + " from table was not possible");
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
