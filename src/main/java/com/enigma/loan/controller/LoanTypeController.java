package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.model.dto.request.LoanTypeRequest;
import com.enigma.loan.model.dto.response.LoanTypeResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = APIUrl.LOAN_TYPE_API)
public class LoanTypeController {
    public ResponseEntity<LoanTypeResponse> createLoanType(LoanTypeRequest loanTypeRequest) {
        return null;
    }

    public ResponseEntity<List<LoanTypeResponse>> getAllLoanTypes() {
        return null;
    }

    public ResponseEntity<LoanTypeResponse> getLoanTypeById(String id) {
        return null;
    }

    public ResponseEntity<String> deleteLoanTypeById(String id) {
        return null;
    }
    public ResponseEntity<LoanTypeResponse> updateLoanType(LoanTypeRequest loanTypeRequest) {
        return null;
    }
}
