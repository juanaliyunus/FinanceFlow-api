package com.enigma.loan.dto.response;

import com.enigma.loan.entity.LoanTransaction;
import com.enigma.loan.entity.LoanTransactionDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionalDetailResponse {
    private String id;
    private Long transactionDate;
    private Double nominal;
    private String loanStatus; // enum
    private Long createdAt;
    private Long updatedAt;
}
