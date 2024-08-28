package com.enigma.loan.dto.request;

import com.enigma.loan.entity.Customer;
import com.enigma.loan.entity.InstalmentType;
import com.enigma.loan.entity.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanTransactionRequest {
    private LoanType loanType;

    private InstalmentType instalmentType;

    private Customer customer;

    private Double nominal;

}
