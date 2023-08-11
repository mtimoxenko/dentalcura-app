package clinic.persistence;

import clinic.entities.Paciente;
import clinic.sql.SQLQueries;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements IDao<Paciente>{

    private final static Logger LOGGER = Logger.getLogger(PacienteDAOH2.class);
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

            statement.execute(SQLQueries.PACIENTE.getCreateTable());

            statement.close();
            connection.close();
            LOGGER.info("Se creo la tabla PACIENTE en la DB.");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("Algo salio mal durante la creacion de la tabla PACIENTE... :( " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.PACIENTE.getInsertCustom());

            preparedStatement.setLong(1,paciente.id());
            preparedStatement.setString(2,paciente.nombre());
            preparedStatement.setString(3,paciente.apellido());
            preparedStatement.setString(4,paciente.domicilio());
            preparedStatement.setInt(5, paciente.dni());
            preparedStatement.setString(6, paciente.fechaAlta());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            LOGGER.info("Paciente " + paciente.nombre() + " " + paciente.apellido() + " fue agregado correctamente a la DB.");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("No fue posible agregar paciente " + paciente.nombre() + " " + paciente.apellido() + " a la DB... :(" + e);
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Paciente> listarTodos() {

        Connection connection;
        PreparedStatement preparedStatement;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement(SQLQueries.PACIENTE.getSelectAll());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong(1);
                String nombre = resultSet.getString(2);
                String apellido = resultSet.getString(3);
                String domicilio = resultSet.getString(4);
                int dni = resultSet.getInt(5);
                String fechaAlta = resultSet.getString(6);

                Paciente paciente = new Paciente(id,nombre, apellido, domicilio, dni, fechaAlta);
                pacientes.add(paciente);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error("No fue posible extraer los datos de la DB... :(" + e);
            throw new RuntimeException(e);
        }

        LOGGER.info("Extrayendo registros de la DB...");
        pacientes.forEach(LOGGER::info);
        return pacientes;
    }

    @Override
    public String toString() {
        return "Data Base H2";
    }
}
