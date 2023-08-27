package clinic.persistence;

import clinic.entities.Appointment;
import clinic.entities.Dentist;
import clinic.entities.Patient;
import clinic.sql.SQLQueries;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOH2 implements IDao<Appointment>{

    private final static Logger LOGGER = Logger.getLogger(AppointmentDAOH2.class);
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
            LOGGER.info("Appointment table was created in DB");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Something went wrong... :( " + e);
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

            LOGGER.info("Data inserted in a table: " + appointment);
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Something went wrong... :( " + e);
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

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Something went wrong... :( " + e);
            throw new RuntimeException(e);
        }

        LOGGER.info("Retrieving data from the database... ");
        appointments.forEach(LOGGER::info);
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


        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Something went wrong... :( " + e);
            throw new RuntimeException(e);
        }

        LOGGER.info("Selecting by id " + "(" + id + ") " + appointment);
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

            LOGGER.info("Data UPDATED in table by id " + "(" + appointment.getId() + ")");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Something went wrong... :( " + e);
            throw new RuntimeException(e);
        }

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

            LOGGER.info("Data DELETED from table by id " + "(" + id + ")");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Something went wrong... :( " + e);
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public String toString() {
        return "H2 relational database";
    }


}
