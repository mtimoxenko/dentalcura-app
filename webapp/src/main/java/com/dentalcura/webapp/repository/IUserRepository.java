package com.dentalcura.webapp.repository;

import com.dentalcura.webapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository <User, Long> {
}
