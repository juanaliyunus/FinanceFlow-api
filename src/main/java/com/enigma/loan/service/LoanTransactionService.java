package com.enigma.loan.service;

import com.enigma.loan.dto.request.LoanTransactionApprovedRequest;
import com.enigma.loan.dto.request.LoanTransactionRequest;
import com.enigma.loan.dto.response.LoanTransactionResponse;
import com.enigma.loan.dto.response.PaymentResponse;

public interface LoanTransactionService {
    LoanTransactionResponse requestLoanTransaction(LoanTransactionRequest loanTransactionRequest);
    LoanTransactionResponse getTransaction(String transactionId);
    LoanTransactionResponse approveTransactionRequestByAdminId(String adminId, LoanTransactionApprovedRequest request);
    PaymentResponse payInstalment(String trxId);
}
