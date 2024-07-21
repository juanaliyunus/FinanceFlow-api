package com.enigma.loan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "trx_loan_detail")
public class LoanTransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Long transactionDate;

    private Double nominal;

    @ManyToOne
    @JsonIgnore
    @JsonManagedReference
    @JoinColumn(name = "loan_transaction_id")
    private LoanTransaction loanTransaction;

    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus; // enum

    private Long createdAt;

    private Long updatedAt;

    @ManyToOne
    @JoinColumn(name = "guarantee_picture_id")
    private GuaranteePicture guaranteePicture;

    public enum LoanStatus {
        PAID,
        UNPAID
    }
}
