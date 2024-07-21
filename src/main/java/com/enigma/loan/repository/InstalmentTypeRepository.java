package com.enigma.loan.repository;

import com.enigma.loan.entity.InstalmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstalmentTypeRepository extends JpaRepository<InstalmentType, String> {
    Optional<InstalmentType> findByInstalmentType(InstalmentType.EInstalmentType instalmentType);
}
