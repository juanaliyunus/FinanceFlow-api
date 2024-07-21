package com.enigma.loan.repository;

import com.enigma.loan.entity.LoanTransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionDetailRepository extends JpaRepository<LoanTransactionDetail, String> {
}
