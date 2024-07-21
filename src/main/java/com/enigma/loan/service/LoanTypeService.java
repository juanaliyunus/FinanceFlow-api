package com.enigma.loan.service;

import com.enigma.loan.dto.request.LoanTypeRequest;
import com.enigma.loan.dto.response.LoanTypeResponse;
import com.enigma.loan.entity.LoanType;

import java.util.List;

public interface LoanTypeService {
    LoanTypeResponse createLoanType(LoanTypeRequest loanTypeRequest);
    LoanTypeResponse updateLoanType(LoanTypeRequest loanTypeRequest);
    List<LoanTypeResponse> getAllLoanTypes();
    LoanTypeResponse getLoanTypeById(String id);
    void deleteLoanTypeById(String id);
}
