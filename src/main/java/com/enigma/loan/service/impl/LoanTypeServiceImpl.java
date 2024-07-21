package com.enigma.loan.service.impl;

import com.enigma.loan.dto.request.LoanTypeRequest;
import com.enigma.loan.dto.response.LoanTypeResponse;
import com.enigma.loan.entity.LoanType;
import com.enigma.loan.exception.ResourceNotFoundException;
import com.enigma.loan.repository.LoanTypeRepository;
import com.enigma.loan.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;

    @Override
    public LoanTypeResponse createLoanType(LoanTypeRequest loanTypeRequest) {
        LoanType loanType = LoanType.builder()
                .type(loanTypeRequest.getType())
                .maxLoan(loanTypeRequest.getMaxLoan())
                .build();
        LoanType createLoanType = loanTypeRepository.saveAndFlush(loanType);
        return LoanTypeResponse.builder()
                .id(createLoanType.getId())
                .type(createLoanType.getType())
                .maxLoan(createLoanType.getMaxLoan())
                .build();
    }

    @Override
    public LoanTypeResponse updateLoanType(LoanTypeRequest loanTypeRequest) {
        LoanType loanType = loanTypeRepository.findById(loanTypeRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("Loan type not found"));
        loanType.setType(loanTypeRequest.getType());
        loanType.setMaxLoan(loanTypeRequest.getMaxLoan());
        LoanType updateLoanType = loanTypeRepository.saveAndFlush(loanType);
        return LoanTypeResponse.builder()
                .id(updateLoanType.getId())
                .type(updateLoanType.getType())
                .maxLoan(updateLoanType.getMaxLoan())
                .build();
    }

    @Override
    public List<LoanTypeResponse> getAllLoanTypes() {
        return loanTypeRepository.findAll().stream()
                .map(loanType -> LoanTypeResponse.builder()
                        .id(loanType.getId())
                        .type(loanType.getType())
                        .maxLoan(loanType.getMaxLoan())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public LoanTypeResponse getLoanTypeById(String id) {
        LoanType loanType = loanTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan type not found"));
        return LoanTypeResponse.builder()
                .id(loanType.getId())
                .type(loanType.getType())
                .maxLoan(loanType.getMaxLoan())
                .build();
    }

    @Override
    public void deleteLoanTypeById(String id) {
        LoanType loanType = loanTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan type not found"));
        loanTypeRepository.delete(loanType);
    }
}
