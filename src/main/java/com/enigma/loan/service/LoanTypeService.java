package com.enigma.loan.service;

import com.enigma.loan.model.dto.request.LoanTypeRequest;
import com.enigma.loan.model.dto.response.LoanTypeResponse;
import com.enigma.loan.model.entity.LoanType;

import java.util.List;

public interface LoanTypeService {
    LoanTypeResponse createLoanType(LoanTypeRequest loanTypeRequest);
    LoanTypeResponse updateLoanType(LoanTypeRequest loanTypeRequest);
    List<LoanTypeResponse> getAllLoanTypes();
    LoanTypeResponse getLoanTypeById(Long id);
    void deleteLoanTypeById(String id);

    LoanType findLoanTypeById(String id);
}
