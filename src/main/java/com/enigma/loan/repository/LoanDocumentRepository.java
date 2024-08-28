package com.enigma.loan.repository;

import com.enigma.loan.entity.LoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDocumentRepository extends JpaRepository<LoanDocument, String> {
}
