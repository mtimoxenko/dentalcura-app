package com.dentalcura.webapp.repository;

import com.dentalcura.webapp.model.Address;
import com.dentalcura.webapp.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistRepository extends JpaRepository <Dentist, Long> {
}
