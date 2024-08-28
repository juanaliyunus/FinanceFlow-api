package com.enigma.loan.service.impl;

import com.enigma.loan.dto.request.LoanTransactionApprovedRequest;
import com.enigma.loan.dto.request.LoanTransactionRequest;
import com.enigma.loan.dto.response.LoanTransactionResponse;
import com.enigma.loan.dto.response.PaymentResponse;
import com.enigma.loan.entity.*;
import com.enigma.loan.exception.ResourceNotFoundException;
import com.enigma.loan.repository.LoanTransactionDetailRepository;
import com.enigma.loan.repository.LoanTransactionRepository;
import com.enigma.loan.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoanTransactionServiceImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanTransactionDetailRepository loanTransactionDetailRepository;
    private final CustomerService customerService;
    private final InstalmentTypeService instalmentTypeService;
    private final LoanTypeService loanTypeService;
    private final AdminService adminService;
    private final RestTemplate restTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoanTransactionResponse requestLoanTransaction(LoanTransactionRequest loanTransactionRequest) {
        LoanType loanTypeById = loanTypeService.getLoanTypeById(loanTransactionRequest.getLoanType().getId()).toEntity();
        InstalmentType instalmentTypeId = instalmentTypeService.getInstalmentTypeById(loanTransactionRequest.getInstalmentType().getId()).toInstalmentTypeEntity();
        Customer customerId = customerService.getCustomerById(loanTransactionRequest.getCustomer().getId()).toCustomerEntity();

        long currentTime = System.currentTimeMillis();

        LoanTransaction loanTransaction = LoanTransaction.builder()
                .loanType(loanTypeById)
                .instalmentType(instalmentTypeId)
                .customer(customerId)
                .nominal(Double.valueOf(loanTransactionRequest.getNominal()))
                .createdAt(currentTime)
                .updatedAt(currentTime)
                .build();

        LoanTransaction createLoanTransaction = loanTransactionRepository.saveAndFlush(loanTransaction);

        return convertToLoanTransactionResponse(createLoanTransaction);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoanTransactionResponse getTransaction(String transactionId) {
        return convertToLoanTransactionResponse(findByIdOrThrowNotFound(transactionId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoanTransactionResponse approveTransactionRequestByAdminId(String adminId, LoanTransactionApprovedRequest request) {
        Admin admin = adminService.getAdminById(adminId);
        LoanTransaction loanTransactionRequest = findByIdOrThrowNotFound(request.getLoanTransactionId());

        double interestRate = request.getInterestRates() / 100.0;
        double nominal = loanTransactionRequest.getNominal() + (loanTransactionRequest.getNominal() * interestRate);

        long currentTime = System.currentTimeMillis();

        LoanTransactionDetail loanTransactionDetail = LoanTransactionDetail.builder()
                .loanStatus(LoanTransactionDetail.LoanStatus.UNPAID)
                .transactionDate(currentTime)
                .nominal(nominal)
                .createdAt(currentTime)
                .updatedAt(currentTime)
                .loanTransaction(loanTransactionRequest)
                .build();

        loanTransactionRequest.setApprovedAt(currentTime);
        loanTransactionRequest.setApprovedBy(admin.getName());
        loanTransactionRequest.setApprovalStatus(LoanTransaction.ApprovalStatus.APPROVED);
        loanTransactionRequest.setUpdatedAt(currentTime);

        loanTransactionRequest.addLoanTransactionDetail(loanTransactionDetail);

        loanTransactionDetailRepository.saveAndFlush(loanTransactionDetail);
        loanTransactionRepository.saveAndFlush(loanTransactionRequest);

        return convertToLoanTransactionResponse(loanTransactionRequest);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public PaymentResponse payInstalment(String trxId) {
        // Temukan detail transaksi dengan ID yang diberikan
        LoanTransactionDetail loanTransactionDetail = findByIdTrxDetailOrThrowNotFound(trxId);

        // Hanya update status transaksi menjadi PAID
        loanTransactionDetail.setLoanStatus(LoanTransactionDetail.LoanStatus.PAID);
        loanTransactionDetailRepository.saveAndFlush(loanTransactionDetail);

        // Kembalikan respons yang sesuai (tanpa token dan redirectUrl)
        return PaymentResponse.builder()
                .token(null) // Tidak ada token karena tidak menghubungi Midtrans
                .redirectUrl(null) // Tidak ada URL redirect karena tidak menghubungi Midtrans
                .build();
    }




    private LoanTransactionResponse convertToLoanTransactionResponse(LoanTransaction loanTransaction) {
        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .loanTypeId(loanTransaction.getLoanType().getId())
                .instalmentTypeId(loanTransaction.getInstalmentType().getId())
                .customerId(loanTransaction.getCustomer().getId())
                .nominal(loanTransaction.getNominal())
                .approvedAt(loanTransaction.getApprovedAt())
                .approvedBy(loanTransaction.getApprovedBy())
                .approvalStatus(String.valueOf(loanTransaction.getApprovalStatus()))
                .loanTransactionDetail(loanTransaction.getLoanTransactionDetails())
                .createdAt(loanTransaction.getCreatedAt())
                .updatedAt(loanTransaction.getUpdatedAt())
                .build();
    }

    private LoanTransaction findByIdOrThrowNotFound(String id) {
        return loanTransactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }

    private LoanTransactionDetail findByIdTrxDetailOrThrowNotFound(String id) {
        return loanTransactionDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }
}
