package clinic.persistence;

import clinic.entities.Odontologo;
import org.apache.log4j.Logger;
import clinic.sql.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements IDao<Odontologo>{

    private final static Logger LOGGER = Logger.getLogger(OdontologoDAOH2.class);
    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    private final static String DB_URL = "jdbc:h2:~/test";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    public void crearTablas(){
        Connection connection;
        Statement statement;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            statement = connection.createStatement();

            statement.execute(SQLQueries.ODONTOLOGO.getCreateTable());

            statement.close();
            connection.close();
            LOGGER.info("Se creo la tabla ODONTOLOGO en la DB.");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Algo salio mal durante la creacion de la tabla ODONTOLOGO... :( " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.ODONTOLOGO.getInsertCustom());

            preparedStatement.setLong(1,odontologo.id());
            preparedStatement.setString(2,odontologo.nombre());
            preparedStatement.setString(3,odontologo.apellido());
            preparedStatement.setInt(4, odontologo.numeroMatricula());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            LOGGER.info("Odontologo " + odontologo.nombre() + " " + odontologo.apellido() + " fue agregado correctamente a la DB.");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("No fue posible agregar odontologo " + odontologo.nombre() + " " + odontologo.apellido() + " a la DB... :(" + e);
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Odontologo> listarTodos() {

        Connection connection;
        PreparedStatement preparedStatement;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.ODONTOLOGO.getSelectAll());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String nombre = resultSet.getString(2);
                String apellido = resultSet.getString(3);
                int matricula = resultSet.getInt(4);

                Odontologo odontologo = new Odontologo(id,nombre, apellido,matricula);
                odontologos.add(odontologo);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("No fue posible extraer los datos de la DB... :(" + e);
            throw new RuntimeException(e);
        }

        LOGGER.info("Extrayendo registros de la DB...");
        odontologos.forEach(LOGGER::info);
        return odontologos;
    }

    @Override
    public String toString() {
        return "Data Base H2";
    }
}
