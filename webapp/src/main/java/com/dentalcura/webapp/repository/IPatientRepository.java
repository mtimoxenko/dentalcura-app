package com.dentalcura.webapp.repository;

import com.dentalcura.webapp.model.Dentist;
import com.dentalcura.webapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository <Patient, Long> {
}
