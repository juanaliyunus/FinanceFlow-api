package com.enigma.loan.repository;

import com.enigma.loan.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, String> {
}
