package com.enigma.loan.repository;

import com.enigma.loan.entity.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTypeRepository extends JpaRepository<LoanType, String> {
}
