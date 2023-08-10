package clinic.sql;


public enum SQLQueries {

    ODONTOLOGO (
            "DROP TABLE IF EXISTS ODONTOLOGO; CREATE TABLE ODONTOLOGO(ID BIGINT PRIMARY KEY, NOMBRE VARCHAR(255), APELLIDO VARCHAR(255), MATRICULA INT);",
            "INSERT INTO ODONTOLOGO VALUES(?,?,?,?);",
            "SELECT * FROM ODONTOLOGO",
            "SELECT COUNT(*) FROM ODONTOLOGO;"
    );

    // ----    Statement Interface   ----
    // DDL - Data Definition Language
    // CREATE - DROP - ALTER
    private final String createTable;

    // ----    PreparedStatement Interface    ----
    // DML - Data Manipulation Language
    // SELECT - INSERT - UPDATE - DELETE
    private final String insertCustom;
    private final String selectAll;
    private final String selectAllRows;

    SQLQueries(String createTable, String insertCustom, String selectAll, String selectAllRows) {
        this.createTable = createTable;
        this.insertCustom = insertCustom;
        this.selectAll = selectAll;
        this.selectAllRows = selectAllRows;
    }

    public String getSelectAllRows() {
        return selectAllRows;
    }

    public String getCreateTable() {
        return createTable;
    }

    public String getSelectAll() {
        return selectAll;
    }

    public String getInsertCustom() {
        return insertCustom;
    }

}
