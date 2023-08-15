package com.dentalcura.bookingapp.sql;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SQLQueries {

    DENTIST(
            "DROP TABLE IF EXISTS dentist; CREATE TABLE dentist(id BIGINT PRIMARY KEY, name VARCHAR(255), surname VARCHAR(255), license_num INT);",
            "INSERT INTO dentist VALUES(?,?,?,?);",
            "SELECT * FROM dentist",
            "SELECT * FROM dentist WHERE id=?",
            "UPDATE dentist SET name=?, surname=?, license_num=? WHERE id=?;",
            "DELETE FROM dentist WHERE id=?"
    ),
    PATIENT(
            "DROP TABLE IF EXISTS patient; CREATE TABLE patient(id BIGINT PRIMARY KEY, name VARCHAR(255), surname VARCHAR(255), address VARCHAR(255), ni_num INT, reg_date VARCHAR(255));",
            "INSERT INTO patient VALUES(?,?,?,?,?,?);",
            "SELECT * FROM patient",
            "SELECT * FROM patient WHERE id=?",
            "UPDATE patient SET name=?, surname=?, address=?, ni_num=?, reg_date=? WHERE id=?;",
            "DELETE FROM patient WHERE id=?"
    ),
    APPOINTMENT(
            "DROP TABLE IF EXISTS appointment; CREATE TABLE appointment(id BIGINT PRIMARY KEY, date VARCHAR(255), patient_id BIGINT, CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patient(id), dentist_id BIGINT, CONSTRAINT fk_appointment_dentist FOREIGN KEY (dentist_id) REFERENCES dentist(id));",
            "INSERT INTO appointment VALUES(?,?,?,?);",
            "SELECT * FROM appointment",
            "SELECT * FROM appointment WHERE id=?",
            "UPDATE appointment SET date=?, patient_id=?, dentist_id=? WHERE id=?;",
            "DELETE FROM appointment WHERE id=?"
    );


    private final String createTable;
    private final String insertCustom;
    private final String selectAll;
    private final String selectById;
    private final String updateById;
    private final String deleteById;

}
