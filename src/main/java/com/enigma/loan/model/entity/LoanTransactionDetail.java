package com.enigma.loan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanTransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Long transactionDate;
    private Double nominal;

    @ManyToOne
    @JoinColumn(name = "loan_transaction_id")
    private LoanTransaction loanTransaction;

    private LoanStatus loanStatus; // enum
    private Long createdAt;
    private Long updatedAt;

    @ManyToOne
    @JoinColumn(name = "guarantee_picture_id")
    private GuaranteePicture guaranteePicture;

    enum LoanStatus {
        PAID,
        UNPAID
    }
}
