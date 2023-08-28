package com.dentalcura.webapp.repository;

import com.dentalcura.webapp.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository <Address, Long> {
}
