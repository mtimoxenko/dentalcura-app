package test;

import org.clinic.sql.SQLQueries;
import org.junit.Test;

import java.util.logging.Logger;
import java.sql.*;

import static org.junit.Assert.assertEquals;


public class DentistDAOH2Test {
    private final static Logger LOGGER = Logger.getLogger(String.valueOf(DentistDAOH2Test.class));
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    @Test
    void listarTodos() {
        LOGGER.info("Iniciando TEST");
        Connection connection;
        PreparedStatement preparedStatement;


        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.DENTIST.getSelectAll());
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;

            while (resultSet.next()){
                count++;
            }

            assertEquals(count, 2);
            LOGGER.info("Test OK!");
            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.info("Test fallo... :_" + e);
            throw new RuntimeException(e);
        }




    }



}