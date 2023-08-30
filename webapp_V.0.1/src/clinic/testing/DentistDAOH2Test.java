package clinic.testing;


import clinic.sql.SQLQueries;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.sql.*;


import static org.junit.jupiter.api.Assertions.*;

class DentistDAOH2Test {
    private final static Logger LOGGER = Logger.getLogger(DentistDAOH2Test.class);
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
            LOGGER.error("Test fallo... :_" + e);
            throw new RuntimeException(e);
        }




    }



}