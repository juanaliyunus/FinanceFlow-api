package com.enigma.loan.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "loan_type")
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
    private ApprovalStatus approvalStatus;

//    @OneToMany
//    @JoinColumn(name =" loanTransactionDetails_id")
//    private List<LoanTransactionDetail> loanTransactionDetails;
    private Long createdAt;
    private Long updatedAt;


    enum ApprovalStatus {
        APPROVED,
        REJECTED
    }
}
