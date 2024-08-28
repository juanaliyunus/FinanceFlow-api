package com.enigma.loan.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trx_loan")
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "instalment_type_id")
    private InstalmentType instalmentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Double nominal;

    private Long approvedAt;

    private String approvedBy;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @OneToMany(mappedBy = "loanTransaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanTransactionDetail> loanTransactionDetails = new ArrayList<>();


    private Long createdAt;

    private Long updatedAt;

    public enum ApprovalStatus {
        APPROVED,
        REJECTED
    }

    public void addLoanTransactionDetail(LoanTransactionDetail detail) {
        loanTransactionDetails.add(detail);
        detail.setLoanTransaction(this);
    }

    public void removeLoanTransactionDetail(LoanTransactionDetail detail) {
        loanTransactionDetails.remove(detail);
        detail.setLoanTransaction(null);
    }
}
