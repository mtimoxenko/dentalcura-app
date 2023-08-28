package com.dentalcura.webapp.repository;

import com.dentalcura.webapp.model.Address;
import com.dentalcura.webapp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepository extends JpaRepository <Appointment, Long> {
}
