package com.enigma.loan.dto.response;

import com.enigma.loan.entity.Customer;
import com.enigma.loan.entity.InstalmentType;
import com.enigma.loan.entity.LoanTransactionDetail;
import com.enigma.loan.entity.LoanType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class LoanTransactionResponse {
    private String id;
    private String loanTypeId;
    private String instalmentTypeId;
    private String customerId;
    private Double nominal;
    private Long approvedAt;
    private String approvedBy;
    private String approvalStatus;
    private List<LoanTransactionDetail> loanTransactionDetail;
    private Long createdAt;
    private Long updatedAt;
}