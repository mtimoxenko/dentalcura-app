package com.dentalcura.webapp.repository;

import com.dentalcura.webapp.model.Address;
import com.dentalcura.webapp.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDentistRepository extends JpaRepository <Dentist, Long> {
}
