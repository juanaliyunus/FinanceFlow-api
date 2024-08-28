package com.enigma.loan.controller;

import com.enigma.loan.constant.APIUrl;
import com.enigma.loan.dto.request.LoanTransactionApprovedRequest;
import com.enigma.loan.dto.request.LoanTransactionRequest;
import com.enigma.loan.dto.response.CommonResponse;
import com.enigma.loan.dto.response.LoanTransactionResponse;
import com.enigma.loan.dto.response.PaymentResponse;
import com.enigma.loan.service.LoanTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = APIUrl.TRANSACTION_API)
public class TransactionController {
    private final LoanTransactionService loanTransactionService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> createRequestLoan(@RequestBody LoanTransactionRequest loanTransactionRequest) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.requestLoanTransaction(loanTransactionRequest);
        CommonResponse<LoanTransactionResponse> commonResponse =CommonResponse.<LoanTransactionResponse>builder()
                .message("Success creat request loan")
                .data(loanTransactionResponse)
                .build();
    return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> getLoanTransactionById(@PathVariable String id) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.getTransaction(id);
        CommonResponse<LoanTransactionResponse> commonResponse = CommonResponse.<LoanTransactionResponse>builder()
                .message("Success getting loan transaction by id = " + id)
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.ok(commonResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
    @PutMapping("/{adminId}/approve")
    public ResponseEntity<CommonResponse<LoanTransactionResponse>> approveLoanTransactionRequestByAdmin(@PathVariable String adminId, @RequestBody LoanTransactionApprovedRequest request) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.approveTransactionRequestByAdminId(adminId,request);
        CommonResponse<LoanTransactionResponse> commonResponse = CommonResponse.<LoanTransactionResponse>builder()
                .message("Success approving loan transaction by admin id = " + adminId)
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.ok(commonResponse);
    }
    @PutMapping("/{trxId}/pay")
    public ResponseEntity<CommonResponse<PaymentResponse>> payment(@PathVariable String trxId){
        PaymentResponse paymentResponse = loanTransactionService.payInstalment(trxId);
        CommonResponse<PaymentResponse> commonResponse = CommonResponse.<PaymentResponse>builder()
                .message("Payment Successful")
                .data(paymentResponse)
                .build();
        return ResponseEntity.ok(commonResponse);  // return payment response to client
    }
}
