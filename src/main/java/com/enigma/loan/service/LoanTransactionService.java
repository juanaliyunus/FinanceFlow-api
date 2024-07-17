package com.enigma.loan.service;

import com.enigma.loan.model.dto.request.LoanTransactionRequest;
import com.enigma.loan.model.dto.response.LoanTransactionResponse;
import com.enigma.loan.model.entity.LoanTransaction;

import java.util.List;

public interface LoanTransactionService {
    LoanTransactionResponse requestLoanTransaction(LoanTransactionRequest loanTransactionRequest);
    LoanTransactionResponse getTransaction(String transactionId);
    LoanTransactionResponse approveTransactionRequestByAdminId(String adminId);
    LoanTransactionResponse PayInstalment (LoanTransactionRequest loanTransactionRequest);

    LoanTransaction findTransactionById(String transactionId);
}
