package com.enigma.loan.repository;

import com.enigma.loan.model.entity.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAppRepository extends JpaRepository<UserApp, String> {
    Optional<UserApp> findByEmail(String email);
}
